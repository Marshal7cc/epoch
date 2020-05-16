package com.marshal.epoch.lock.config;

import com.marshal.epoch.lock.DistributedLocker;
import com.marshal.epoch.lock.component.RedisDistributedLocker;
import com.marshal.epoch.lock.propertis.DistributedLockProperty;
import com.marshal.epoch.lock.propertis.JedisPoolProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @auth: Marshal
 * @date: 2020/4/17
 * @desc: epoch 分布式锁
 */
@EnableConfigurationProperties({DistributedLockProperty.class})
public class LockAutoConfiguration {

    @Autowired
    private DistributedLockProperty distributedLockProperty;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.distributed-lock", name = {"type"}, havingValue = "redis")
    public JedisPool jedisPool() {
        JedisPoolProperty jedisPoolProperty = distributedLockProperty.getJedis();
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(jedisPoolProperty.getMaxTotal());
        // 设置最大空闲数
        config.setMaxIdle(jedisPoolProperty.getMaxIdle());
        // 设置最大等待时间
        config.setMaxWaitMillis(jedisPoolProperty.getMaxWaitMillis());
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);

        JedisPool pool = new JedisPool(config, jedisPoolProperty.getHost(),
                jedisPoolProperty.getPort(), 3000, jedisPoolProperty.getPassword(),
                jedisPoolProperty.getDatabase());
        return pool;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.distributed-lock", name = {"type"}, havingValue = "redis")
    public DistributedLocker redisDistributedLocker(JedisPool jedisPool) {
        RedisDistributedLocker distributedLocker = new RedisDistributedLocker();
        distributedLocker.setJedisPool(jedisPool);
        return distributedLocker;
    }

}
