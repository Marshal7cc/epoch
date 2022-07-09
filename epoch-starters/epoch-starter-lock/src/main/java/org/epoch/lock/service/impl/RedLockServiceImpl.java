package org.epoch.lock.service.impl;


import org.epoch.lock.provider.LockInfo;
import org.epoch.lock.service.LockService;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 红锁
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
        RLock[] locks = new RLock[lockInfo.getKeys().size()];
        for (int i = 0; i < lockInfo.getKeys().size(); i++) {
            locks[i] = redissonClient.getLock(lockInfo.getKeys().get(i));
        }
        try {
            RedissonRedLock lock = new RedissonRedLock(locks);
            return lock.tryLock(lockInfo.getWaitTime(), lockInfo.getLeaseTime(), lockInfo.getTimeUnit());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unlock(LockInfo lockInfo) {
        RLock[] locks = new RLock[lockInfo.getKeys().size()];
        for (int i = 0; i < lockInfo.getKeys().size(); i++) {
            locks[i] = redissonClient.getLock(lockInfo.getKeys().get(i));
        }
        RedissonRedLock lock = new RedissonRedLock(locks);
        lock.unlock();
    }
}
