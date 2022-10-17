package org.epoch.data.repository;

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

/**
 * @author Marshal
 * @since 2022/10/13
 */
@Slf4j
public abstract class AbstractParallelRepository<T, ID> implements ParallelRepository<T, ID>, AopProxy<BaseRepository<T, ID>> {
    @Override
    public List<T> parallelSave(List<T> entities) {
        return this.parallelSave(entities, getDefaultThreshold(entities.size()));
    }

    @Override
    @SneakyThrows
    public List<T> parallelSave(List<T> entities, int threshold) {
        int size = entities.size();
        if (size < threshold) {
            this.saveAll(entities);
        }

        List<List<T>> lists = Lists.partition(entities, threshold);
        int workerSize = lists.size();
        if (workerSize > MAX_WORKER_SIZE) {
            throw new BaseException("worker size out of limit.");
        }

        CountDownLatch latch = new CountDownLatch(workerSize);
        CountDownLatch mainLatch = new CountDownLatch(Digital.ONE);
        MultiThreadTransactionManager transactionManager = new MultiThreadTransactionManager(latch, mainLatch, false);

        List<Future<List<T>>> futures = new ArrayList<>(workerSize);
        ThreadPoolExecutor workerFactory = new ThreadPoolExecutor(MAX_WORKER_SIZE, MAX_WORKER_SIZE, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
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

        List<T> results = new ArrayList<>();
        for (Future<List<T>> future : futures) {
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
    private class Worker implements Callable<List<T>> {
        private List<T> entities;
        private BaseRepository<T, ID> repository;
        private MultiThreadTransactionManager transactionManager;

        @Override
        public List<T> call() {
            try {
                repository.saveAll(entities);
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

            return entities;
        }
    }
}
