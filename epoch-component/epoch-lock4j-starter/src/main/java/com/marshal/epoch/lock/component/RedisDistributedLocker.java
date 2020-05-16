package com.marshal.epoch.lock.component;

import com.marshal.epoch.lock.DistributedLocker;
import com.marshal.epoch.lock.component.redisson.RedissonClientHolder;
import com.marshal.epoch.lock.util.IdWorker;
import lombok.Data;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @auth: Marshal
 * @date: 2020/4/17
 * @desc: redis 分布式锁实现
 */
@Data
public class RedisDistributedLocker implements DistributedLocker {

    private static Logger logger = LoggerFactory.getLogger(RedisDistributedLocker.class);

    private RedissonClientHolder redissonClientHolder;

    //存储RLock
    private ThreadLocal<RLock> lockHolder = new ThreadLocal<>();

    @Override
    public void lock(String lockKey) {
        RLock lock = getLock(lockKey, false);
        lock.lock();
    }

    @Override
    public boolean tryLock(String lockKey) {
        RLock lock = getLock(lockKey, false);
        return lock.tryLock();
    }

    @Override
    public boolean tryLock(String lockKey, long timeout) {
        RLock lock = getLock(lockKey, false);
        try {
            return lock.tryLock(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.debug("get lock fail");
        }
        return false;
    }

    @Override
    public boolean tryLock(String lockKey, long timeout, long ttl) {
        RLock lock = getLock(lockKey, false);
        try {
            return lock.tryLock(timeout, ttl, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.debug("get lock fail");
        }
        return false;
    }

    @Override
    public boolean tryLock(String lockKey, long timeout, long ttl, boolean fair, boolean async) {
        RLock lock = getLock(lockKey, fair);

        try {
            if (async) {
                return lock.tryLockAsync(timeout, ttl, TimeUnit.MILLISECONDS).get();
            } else {
                return lock.tryLock(timeout, ttl, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            logger.debug("try lock failed");
            return false;
        }
    }

    @Override
    public boolean unlock() {
        if (redissonClientHolder.isStarted()) {
            RLock rLock = lockHolder.get();
            rLock.unlock();
            return true;
        }
        return false;
    }

    /**
     * 获取lock
     *
     * @param lockKey lockKey
     * @param fair    是否公平
     * @return
     */
    private RLock getLock(String lockKey, boolean fair) {
        RedissonClient redisson = redissonClientHolder.getRedisson();
        RLock lock = null;
        if (fair) {
            lock = redisson.getFairLock(lockKey);
        } else {
            lock = redisson.getFairLock(lockKey);
        }

        lockHolder.set(lock);
        return lock;
    }
}
