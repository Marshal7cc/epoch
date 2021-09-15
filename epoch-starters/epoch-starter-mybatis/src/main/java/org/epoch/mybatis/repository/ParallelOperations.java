package org.epoch.mybatis.repository;

import java.util.List;

/**
 * Parallel Operations in Persistence.
 *
 * @author Marshal
 * @since 2021/8/25
 */
public interface ParallelOperations<T> {
    int DEFAULT_THRESHOLD = 2000;
    int THREAD_POOL_SIZE = 1 << 4;

    /**
     * Parallel method for saving operations.
     *
     * @param list records
     */
    void parallelSave(List<T> list);

    /**
     * Parallel method for saving operations with target threshold.
     *
     * @param list      records
     * @param threshold threshold
     */
    void parallelSave(List<T> list, int threshold);

    /**
     * Get default threshold in single thread.
     *
     * @param size size of records
     * @return
     */
    default int getDefaultThreshold(int size) {
        int coreSize = Runtime.getRuntime().availableProcessors();
        if (size < DEFAULT_THRESHOLD) {
            return size;
        }

        return (size + coreSize) / coreSize;
    }
}
