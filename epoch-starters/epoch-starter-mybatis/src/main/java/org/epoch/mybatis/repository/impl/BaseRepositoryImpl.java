package org.epoch.mybatis.repository.impl;


import java.util.List;
import java.util.concurrent.*;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.transaction.TransactionException;
import org.epoch.core.base.BaseConstants;
import org.epoch.core.exception.OptimisticLockException;
import org.epoch.mybatis.repository.BaseRepository;
import org.epoch.mybatis.util.EntityHelper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Marshal
 * @date 2018/1/7
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class BaseRepositoryImpl<T> implements BaseRepository<T>, BaseConstants {

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public List<T> select(int page, int size, T condition) {
        PageHelper.startPage(page, size);
        return mapper.select(condition);
    }

    @Override
    public List<T> select(T condition) {
        return mapper.select(condition);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectOne(T condition) {
        return mapper.selectOne(condition);
    }

    @Override
    public T selectByPrimaryKey(Object record) {
        return mapper.selectByPrimaryKey(record);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) throws OptimisticLockException {
        int updateRows = mapper.updateByPrimaryKey(record);
        checkOptimisticLock(updateRows);
        return updateRows;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) throws OptimisticLockException {
        int updateRows = mapper.updateByPrimaryKeySelective(record);
        checkOptimisticLock(updateRows);
        return updateRows;
    }

    @Override
    public int deleteByPrimaryKey(Object record) {
        return mapper.deleteByPrimaryKey(record);
    }

    @Override
    public int submit(T record) throws OptimisticLockException {
        if (EntityHelper.isPrimaryKeyNull(record)) {
            return this.insertSelective(record);
        } else {
            return this.updateByPrimaryKey(record);
        }
    }

    @Override
    public void batchSubmit(List<T> records) throws OptimisticLockException {
        for (T record : records) {
            submit(record);
        }
    }

    @Override
    public void batchDelete(Object[] keys) {
        for (Object primaryKey : keys) {
            this.deleteByPrimaryKey(primaryKey);
        }
    }

    @Override
    public void batchDelete(List<T> records) {
        records.forEach(this::deleteByPrimaryKey);
    }


    /**
     * 检查数据库乐观锁
     *
     * @param updateRows
     * @throws OptimisticLockException
     */
    private void checkOptimisticLock(int updateRows) throws OptimisticLockException {
        if (updateRows < 1) {
            throw new OptimisticLockException();
        }
    }

    @Override
    public void parallelSave(List<T> list) {
        int defaultThreshold = getDefaultThreshold(list.size());
        log.info("parallel ops: use default threshold :{}", defaultThreshold);

        parallelSave(list, defaultThreshold);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void parallelSave(List<T> list, int threshold) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        int size = list.size();
        if (size <= threshold) {
            log.info("parallel ops: record size is less than threshold: {}, execute in main thread.", threshold);
            batchSubmit(list);
        } else {
            List<List<T>> subLists = Lists.partition(list, threshold);
            int taskCount = subLists.size();
            Assert.isTrue(taskCount < THREAD_POOL_SIZE, "task count may less than default count limit, please adjust threshold.");

            log.info("parallel ops: record size is greater than threshold: {}, execute in {} threads.", threshold, taskCount);

            // Global transaction attribute
            CountDownLatch mainLatch = new CountDownLatch(1);
            CountDownLatch subLatch = new CountDownLatch(taskCount);
            TransactionManager transactionManager = new TransactionManager(mainLatch, subLatch, false);

            ExecutorService executor = new ThreadPoolExecutor(
                    Math.min(taskCount, THREAD_POOL_SIZE),
                    Math.max(taskCount, THREAD_POOL_SIZE),
                    0, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    new NamedThreadFactory("parallel-ops")
            );

            // Submit each task.
            for (List<T> subList : subLists) {
                ParallelTransactionalTask task
                        = new ParallelTransactionalTask(subList, (BaseRepositoryImpl<T>) AopContext.currentProxy(), transactionManager);
                executor.submit(task);
            }

            try {
                subLatch.await();
            } catch (InterruptedException e) {
                transactionManager.setRollback(true);
            } finally {
                mainLatch.countDown();
            }

            if (transactionManager.isRollback()) {
                log.error("parallel ops: save failed, rollback transaction in main thread.");
                throw new TransactionException("parallel ops: save failed, rollback transaction in main thread.");
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("parallel save success.");
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void parallelSave(TransactionManager transactionManager, List<T> list) {
        CountDownLatch mainLatch = transactionManager.getMainLatch();
        CountDownLatch subLatch = transactionManager.getSubLatch();
        try {
            this.batchSubmit(list);
        } catch (Exception e) {
            transactionManager.setRollback(true);
            log.error("parallel ops: save failed, sub thread set attribute 'rollback' to false, thread id: {}, message: {}", Thread.currentThread().getId(), e.getMessage());
        } finally {
            subLatch.countDown();
        }

        try {
            mainLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (transactionManager.isRollback()) {
            log.error("parallel save failed, sub thread rollback transaction.");
            throw new RuntimeException();
        }
    }

    /**
     * TransactionManager: Control transaction in threads.
     */
    @Data
    @AllArgsConstructor
    protected static class TransactionManager {
        private CountDownLatch mainLatch;
        private CountDownLatch subLatch;
        private volatile boolean rollback;
    }


    @AllArgsConstructor
    protected class ParallelTransactionalTask implements Runnable {
        private final List<T> list;
        private final BaseRepositoryImpl<T> baseRepository;
        private final TransactionManager transactionManager;

        @Override
        public void run() {
            baseRepository.parallelSave(transactionManager, list);
        }
    }
}
