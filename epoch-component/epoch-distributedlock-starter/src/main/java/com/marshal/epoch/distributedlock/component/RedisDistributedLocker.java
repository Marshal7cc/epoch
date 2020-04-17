package com.marshal.epoch.distributedlock.component;

import com.marshal.epoch.distributedlock.DistributedLocker;
import com.marshal.epoch.distributedlock.util.IdWorker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @auth: Marshal
 * @date: 2020/4/17
 * @desc: redis 分布式锁实现
 */
@Data
@Slf4j
public class RedisDistributedLocker implements DistributedLocker {

    //redis key 前缀
    private static final String LOCK_KEY_PREFIX = "epoch:distributed-lock:";

    //redis 连接池
    private JedisPool jedisPool;

    //分布式ID生成器
    private IdWorker idWorker = new IdWorker(1, 1);

    //存储生成的标识
    private ThreadLocal<Long> valueThreadLocal = new ThreadLocal<>();

    @Override
    public boolean lock(String lock) {
        return tryLock(lock, 0, 0);
    }

    @Override
    public boolean tryLock(String lock, long timeout) {
        return tryLock(lock, timeout, 0);
    }

    @Override
    public boolean tryLock(String lock, long timeout, long ttl) {
        Assert.isTrue(timeout >= 0 && ttl >= 0, "timeout or ttl can not less than 0");
        Assert.hasText(lock, "lock can not be empty");

        Jedis conn = null;
        try {
            // 1.建立redis连接
            conn = jedisPool.getResource();
            // 2.随机生成一个value
            long value = idWorker.nextId();
            // 3.定义锁的名称
            String lockName = LOCK_KEY_PREFIX + lock;
            // 4.上锁成功之后,锁的超时时间==>ttl
            // 5.定义在没有获取锁之前,锁的超时时间
            if (timeout == 0) {
                while (true) {
                    if (conn.setnx(lockName, String.valueOf(value)).equals(1L)) {
                        if (ttl != 0) {
                            conn.expire(lockName, Integer.parseInt(String.valueOf(ttl)));
                        }
                        valueThreadLocal.set(value);
                        return true;
                    }
                }
            } else {
                Long endTime = System.currentTimeMillis() + timeout;
                while (System.currentTimeMillis() < endTime) {
                    // 6.使用setnx方法设置锁值
                    if (conn.setnx(lockName, String.valueOf(value)).equals(1L)) {
                        valueThreadLocal.set(value);
                        // 7.判断返回结果如果为1,则可以成功获取锁,并且设置锁的超时时间
                        if (ttl != 0) {
                            conn.expire(lockName, Integer.parseInt(String.valueOf(ttl)));
                        }
                        return true;
                    }
                    // 8.否则情况下继续循环等待
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
            conn = null;
        }
        return false;
    }

    @Override
    public boolean release(String lock) {
        Jedis conn = null;
        try {
            // 1.建立redis连接
            conn = jedisPool.getResource();
            // 2.定义锁的名称
            String lockName = LOCK_KEY_PREFIX + lock;
            // 3.如果value与redis中一直直接删除，否则等待超时
            Long value = valueThreadLocal.get();
            if (String.valueOf(value).equals(conn.get(lockName))) {
                conn.del(lockName);
                log.debug("release lock success, lockKey :{},value :{}", lock, value);
                return true;
            }
        } catch (Exception e) {
            log.debug("release lock fail");
            return false;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
