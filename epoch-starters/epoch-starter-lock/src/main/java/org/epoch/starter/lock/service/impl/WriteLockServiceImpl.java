package org.epoch.starter.lock.service.impl;


import org.epoch.starter.lock.provider.LockInfo;
import org.epoch.starter.lock.service.LockService;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 写锁实现
 *
 * @author Marshal
 * @date 2021/12/8
 */
public class WriteLockServiceImpl implements LockService {

    @Qualifier("lockRedissonClient")
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean lock(LockInfo lockInfo) {
        try {
            RReadWriteLock rLock = redissonClient.getReadWriteLock(lockInfo.getName());
            return rLock.writeLock().tryLock(lockInfo.getWaitTime(), lockInfo.getLeaseTime(), lockInfo.getTimeUnit());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unlock(LockInfo lockInfo) {
        RReadWriteLock rLock = redissonClient.getReadWriteLock(lockInfo.getName());
        if (rLock.writeLock().isHeldByCurrentThread()) {
            rLock.writeLock().unlockAsync();
        }
    }
}
