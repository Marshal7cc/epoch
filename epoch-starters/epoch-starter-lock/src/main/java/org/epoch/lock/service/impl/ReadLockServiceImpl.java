package org.epoch.lock.service.impl;


import org.epoch.lock.provider.LockInfo;
import org.epoch.lock.service.LockService;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 读锁
 *
 * @author Marshal
 * @date 2021/12/8
 */
public class ReadLockServiceImpl implements LockService {

    @Qualifier("lockRedissonClient")
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean lock(LockInfo lockInfo) {
        try {
            RReadWriteLock rLock = redissonClient.getReadWriteLock(lockInfo.getName());
            return rLock.readLock().tryLock(lockInfo.getWaitTime(), lockInfo.getLeaseTime(), lockInfo.getTimeUnit());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unlock(LockInfo lockInfo) {
        RReadWriteLock rLock = redissonClient.getReadWriteLock(lockInfo.getName());
        if (rLock.readLock().isHeldByCurrentThread()) {
            rLock.readLock().unlockAsync();
        }
    }
}
