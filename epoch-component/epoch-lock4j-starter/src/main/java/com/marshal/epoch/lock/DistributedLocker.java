package com.marshal.epoch.lock;

/**
 * @author Marshal
 * @desc 分布式锁抽象接口
 * @since 2020/4/17
 */
public interface DistributedLocker {

    /**
     * 获取分布式锁，获取不到的情况下将会自旋重试
     *
     * @param lock
     * @return true if acquire lock,else false
     */
    boolean lock(String lock);

    /**
     * 获取分布式锁，在尝试到达超时时间后会返回false
     *
     * @param lock
     * @param timeout 超时时间
     * @return
     */
    boolean tryLock(String lock, long timeout);

    /**
     * @param lock
     * @param timeout
     * @param ttl     锁的自动过期时间
     * @return
     */
    boolean tryLock(String lock, long timeout, long ttl);

    /**
     * 释放锁
     *
     * @param lock
     * @return
     */
    boolean release(String lock);

}
