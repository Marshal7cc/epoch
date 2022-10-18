package org.epoch.data.service;

import java.util.List;

/**
 * Parallel Operations in Persistence.
 *
 * @author Marshal
 * @since 2021/8/25
 */
public interface BaseParallelService<DOMAIN, ID> extends BaseService<DOMAIN, ID> {
    int DEFAULT_THRESHOLD = 5000;
    int MAX_WORKER_SIZE = 1 << 4;

    /**
     * Parallel method for saving operations.
     *
     * @param domains records
     * @return
     */
    List<DOMAIN> parallelSave(List<DOMAIN> domains);

    /**
     * Parallel method for saving operations with target threshold.
     *
     * @param domains   records
     * @param threshold threshold
     * @return
     */
    List<DOMAIN> parallelSave(List<DOMAIN> domains, int threshold);

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
