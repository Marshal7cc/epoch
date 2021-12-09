package org.epoch.starter.lock.service.impl;


import org.epoch.starter.lock.provider.LockInfo;
import org.epoch.starter.lock.service.LockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 可重入锁
 *
 * @author Marshal
 * @date 2021/12/8
 */
public class ReentrantLockServiceImpl implements LockService {

    @Qualifier("lockRedissonClient")
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean lock(LockInfo lockInfo) {
        try {
            RLock rLock = redissonClient.getLock(lockInfo.getName());
            return rLock.tryLock(lockInfo.getWaitTime(), lockInfo.getLeaseTime(), lockInfo.getTimeUnit());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unlock(LockInfo lockInfo) {
        RLock rLock = redissonClient.getLock(lockInfo.getName());
        if (rLock.isHeldByCurrentThread()) {
            rLock.unlockAsync();
        }
    }
}
