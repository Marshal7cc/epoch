package org.epoch.starter.lock.service.impl;


import org.epoch.starter.lock.provider.LockInfo;
import org.epoch.starter.lock.service.LockService;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 红锁实现类
 *
 * @author Marshal
 * @date 2021/12/8
 */
public class RedLockServiceImpl implements LockService {

    @Qualifier("lockRedissonClient")
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean lock(LockInfo lockInfo) {
        RLock[] lockList = new RLock[lockInfo.getKeys().size()];
        for (int i = 0; i < lockInfo.getKeys().size(); i++) {
            lockList[i] = redissonClient.getLock(lockInfo.getKeys().get(i));
        }
        try {
            RedissonRedLock lock = new RedissonRedLock(lockList);
            return lock.tryLock(lockInfo.getWaitTime(), lockInfo.getLeaseTime(), lockInfo.getTimeUnit());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unlock(LockInfo lockInfo) {
        RLock[] lockList = new RLock[lockInfo.getKeys().size()];
        for (int i = 0; i < lockInfo.getKeys().size(); i++) {
            lockList[i] = redissonClient.getLock(lockInfo.getKeys().get(i));
        }
        RedissonRedLock lock = new RedissonRedLock(lockList);
        lock.unlock();
    }
}
