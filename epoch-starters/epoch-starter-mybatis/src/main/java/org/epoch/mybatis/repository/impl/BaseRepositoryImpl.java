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
import org.epoch.core.base.BaseConstants;
import org.epoch.core.exception.CommonException;
import org.epoch.core.exception.OptimisticLockException;
import org.epoch.mybatis.repository.BaseRepository;
import org.epoch.mybatis.util.EntityHelper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;
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
    @SuppressWarnings("unchecked")
    public void parallelSave(List<T> list, int threshold) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        int size = list.size();
        if (size < threshold) {
            batchSubmit(list);
        } else {
            List<List<T>> subLists = Lists.partition(list, threshold);
            // Global transaction attribute
            CountDownLatch mainLatch = new CountDownLatch(1);
            CountDownLatch subLatch = new CountDownLatch(subLists.size());
            TransactionAttribute transactionAttribute = new TransactionAttribute(mainLatch, subLatch, false);

            ExecutorService executor = new ThreadPoolExecutor(5, 5, 5,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new NamedThreadFactory("parallel-save"));

            try {
                for (List<T> subList : subLists) {
                    TransactionalTask task = new TransactionalTask(subList, (BaseRepositoryImpl<T>) AopContext.currentProxy(), transactionAttribute);
                    executor.submit(task);
                }
                subLatch.await();
            } catch (Exception e) {
                transactionAttribute.setRollback(true);
            } finally {
                mainLatch.countDown();
            }

            if (Boolean.FALSE.equals(transactionAttribute.getRollback())) {
                log.error("parallel save failed, rollback transaction.");
                throw new CommonException("parallel save failed, rollback transaction.");
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("parallel save success.");
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void parallelSave(TransactionAttribute transactionAttribute, List<T> list) {
        CountDownLatch mainLatch = transactionAttribute.getMainLatch();
        CountDownLatch subLatch = transactionAttribute.getSubLatch();
        try {
            this.batchSubmit(list);
        } catch (Exception e) {
            transactionAttribute.setRollback(true);
            log.error("parallel save failed, sub thread rollback transaction.");
        } finally {
            subLatch.countDown();
        }

        try {
            mainLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (transactionAttribute.getRollback()) {
            log.error("parallel save failed, sub thread rollback transaction.");
            throw new RuntimeException();
        }
    }

    @Data
    @AllArgsConstructor
    protected static class TransactionAttribute {
        private CountDownLatch mainLatch;
        private CountDownLatch subLatch;
        private volatile Boolean rollback;
    }

    @AllArgsConstructor
    protected class TransactionalTask implements Runnable {
        private final List<T> list;
        private final BaseRepositoryImpl<T> baseRepository;
        private final TransactionAttribute transactionAttribute;

        @Override
        public void run() {
            baseRepository.parallelSave(transactionAttribute, list);
        }
    }
}
