package org.epoch.mybatis.repository;

import java.util.List;

/**
 * @author Marshal
 * @since 2021/8/25
 */
public interface ParallelOperations<T> {
    /**
     * parallel method for saving records
     *
     * @param list      records
     * @param threshold threshold
     */
    void parallelSave(List<T> list, int threshold);
}
