package com.marshal.epoch.lock;


/**
 * 分布式锁
 *
 * @author Marshal
 * @date 2020/5/16
 */
public interface DistributedLocker {

    /**
     * 获取分布式锁
     *
     * <pre>
     * 如果没有立刻获取锁，该线程将会一直获取锁
     * </pre>
     *
     * @param lockKey
     */
    void lock(String lockKey);

    /**
     * 获取分布式锁
     *
     * <pre>
     * 如果没有立刻获取锁，立刻返回false
     * </pre>
     *
     * @param lockKey
     * @return
     */
    boolean tryLock(String lockKey);

    /**
     * 获取分布式锁，在尝试到达超时时间后会返回false
     *
     * @param lockKey
     * @param timeout 超时时间
     * @return
     */
    boolean tryLock(String lockKey, long timeout);

    /**
     * 尝试获取分布式锁，持续请求时间到达timeout后会返回结果
     *
     * @param lockKey 锁的key
     * @param timeout 持续请求时间
     * @param ttl 持有锁时长到达ttl后，会自动释放该锁
     * @return
     */
    boolean tryLock(String lockKey, long timeout, long ttl);

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey 锁的key
     * @param timeout 持续请求时间
     * @param ttl 锁的自动过期时间
     * @param fair
     * @param async
     * @return
     */
    boolean tryLock(String lockKey, long timeout, long ttl, boolean fair, boolean async);

    /**
     * 释放锁
     *
     * @return
     */
    boolean unlock();

}
