package org.epoch.starter.lock.service.impl;


import org.epoch.starter.lock.provider.LockInfo;
import org.epoch.starter.lock.service.LockService;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 联锁
 *
 * @author Marshal
 * @date 2021/12/8
 */
public class MultiLockServiceImpl implements LockService {

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
            RedissonMultiLock lock = new RedissonMultiLock(locks);
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
        RedissonMultiLock lock = new RedissonMultiLock(locks);
        lock.unlock();
    }
}
