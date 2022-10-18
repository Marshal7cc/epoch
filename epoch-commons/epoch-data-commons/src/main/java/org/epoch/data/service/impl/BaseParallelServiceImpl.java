package org.epoch.data.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.epoch.core.constant.Digital;
import org.epoch.core.exception.BaseException;
import org.epoch.core.proxy.AopProxy;
import org.epoch.core.util.NamedThreadFactory;
import org.epoch.data.repository.BaseRepository;
import org.epoch.data.service.BaseParallelService;
import org.epoch.data.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 * @since 2022/10/13
 */
@Slf4j
public abstract class BaseParallelServiceImpl<R extends BaseRepository<T, ID>, DO, T, ID extends Serializable>
        extends BaseServiceImpl<R, DO, T, ID> implements BaseParallelService<DO, ID>, AopProxy<BaseService<DO, ID>> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DO> parallelSave(List<DO> domains) {
        return this.parallelSave(domains, getDefaultThreshold(domains.size()));
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public List<DO> parallelSave(List<DO> domains, int threshold) {
        int size = domains.size();
        if (size < threshold) {
            log.debug("size less than threshold, save in main thread.");
            this.saveAll(domains);
        }

        List<List<DO>> lists = Lists.partition(domains, threshold);
        int workerSize = lists.size();
        if (workerSize > MAX_WORKER_SIZE) {
            throw new BaseException("worker size out of limit.");
        }

        CountDownLatch latch = new CountDownLatch(workerSize);
        CountDownLatch mainLatch = new CountDownLatch(Digital.ONE);
        MultiThreadTransactionManager transactionManager = new MultiThreadTransactionManager(latch, mainLatch, false);

        List<Future<List<DO>>> futures = new ArrayList<>(workerSize);
        ThreadPoolExecutor workerFactory = new ThreadPoolExecutor(
                MAX_WORKER_SIZE, MAX_WORKER_SIZE, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new NamedThreadFactory("parallel-worker")
        );

        lists.forEach(list -> {
            futures.add(workerFactory.submit(new Worker(list, this.self(), transactionManager)));
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            // Skip this ex.
        } finally {
            mainLatch.countDown();
        }

        if (transactionManager.isRollback()) {
            throw new BaseException("exception happens in a thread, rollback transaction");
        }

        // The time: each thread finish task.
        List<DO> results = new ArrayList<>(size);
        for (Future<List<DO>> future : futures) {
            results.addAll(future.get());
        }
        return results;
    }

    @Data
    @AllArgsConstructor
    private static class MultiThreadTransactionManager {
        private CountDownLatch latch;
        private CountDownLatch mainLatch;
        private boolean rollback;
    }

    @Data
    @AllArgsConstructor
    private class Worker implements Callable<List<DO>> {
        private List<DO> domains;
        private BaseService<DO, ID> service;
        private MultiThreadTransactionManager transactionManager;

        @Override
        public List<DO> call() {
            List<DO> results = null;
            try {
                results = service.saveAll(domains);
            } catch (Exception e) {
                log.info("exception happens in current thread: {}, rollback transaction, message: {}", Thread.currentThread().getId(), e.getMessage());
                transactionManager.setRollback(true);
            } finally {
                // Release resource not only success but also fail.
                transactionManager.getLatch().countDown();
            }

            // Release latch first, then await mail latch.
            try {
                transactionManager.getMainLatch().await();
            } catch (InterruptedException e) {
                // Skip this ex.
            }

            if (transactionManager.isRollback()) {
                throw new BaseException("exception happens in other thread, rollback transaction");
            }

            return results;
        }
    }
}
