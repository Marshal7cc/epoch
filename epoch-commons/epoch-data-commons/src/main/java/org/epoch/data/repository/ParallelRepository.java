package org.epoch.data.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * Parallel Operations in Persistence.
 *
 * @author Marshal
 * @since 2021/8/25
 */
@NoRepositoryBean
public interface ParallelRepository<T> {
    int DEFAULT_THRESHOLD = 5000;
    int THREAD_POOL_SIZE = 1 << 4;

    /**
     * Parallel method for saving operations.
     *
     * @param entities records
     */
    void parallelSave(List<T> entities);

    /**
     * Parallel method for saving operations with target threshold.
     *
     * @param entities  records
     * @param threshold threshold
     */
    void parallelSave(List<T> entities, int threshold);

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
