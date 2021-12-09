package org.epoch.starter.lock.service;


import org.epoch.starter.lock.provider.LockInfo;

/**
 * @author Marshal
 * @date 2021/12/8
 */
public interface LockService {

    /**
     * Acquires the lock.
     *
     * @param lockInfo lock attributes
     * @return result
     */
    boolean lock(LockInfo lockInfo);

    /**
     * Release the lock.
     *
     * @param lockInfo lock attributes
     */
    void unlock(LockInfo lockInfo);
}
