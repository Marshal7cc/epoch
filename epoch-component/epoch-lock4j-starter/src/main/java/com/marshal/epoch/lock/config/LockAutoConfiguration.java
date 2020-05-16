package com.marshal.epoch.lock.config;

import com.marshal.epoch.lock.DistributedLocker;
import com.marshal.epoch.lock.aop.DistributedLockAspect;
import com.marshal.epoch.lock.component.RedisDistributedLocker;
import com.marshal.epoch.lock.component.redisson.RedissonClientHolder;
import com.marshal.epoch.lock.propertis.Lock4jProperties;
import com.marshal.epoch.lock.propertis.RedissonProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


/**
 * @auth: Marshal
 * @date: 2020/4/17
 * @desc: epoch锁(主要是分布式锁)
 */
@EnableConfigurationProperties({Lock4jProperties.class})
public class LockAutoConfiguration {

    @Autowired
    private Lock4jProperties lock4JProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.lock4j", name = {"type"}, havingValue = "redisson")
    RedissonClient redisson() throws IOException {
        RedissonProperties redissonProperties = lock4JProperties.getRedissonProperties();
        Config config = new Config();
        config.useSingleServer()
                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                .setConnectTimeout(redissonProperties.getConnectTimeout())
                .setTimeout(redissonProperties.getTimeout())
                .setRetryAttempts(redissonProperties.getRetryAttempts())
                .setRetryInterval(redissonProperties.getRetryInterval())
                .setPassword(redissonProperties.getPassword())
                .setAddress(redissonProperties.getAddress())
                .setClientName(redissonProperties.getClientName())
                .setSubscriptionConnectionMinimumIdleSize(redissonProperties.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redissonProperties.getSubscriptionConnectionPoolSize())
                .setSubscriptionsPerConnection(redissonProperties.getSubscriptionsPerConnection())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                .setDatabase(redissonProperties.getDatabase())
                .setDnsMonitoringInterval(redissonProperties.getDnsMonitoringInterval());
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.lock4j", name = {"type"}, havingValue = "redisson")
    public DistributedLocker redisDistributedLocker(RedissonClientHolder redissonClientHolder) {
        RedisDistributedLocker distributedLocker = new RedisDistributedLocker();
        distributedLocker.setRedissonClientHolder(redissonClientHolder);
        return distributedLocker;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.lock4j", name = {"type"}, havingValue = "redisson")
    public RedissonClientHolder redissonClientHolder(RedissonClient redissonClient) {
        RedissonClientHolder redissonClientHolder = new RedissonClientHolder();
        redissonClientHolder.setRedisson(redissonClient);
        return redissonClientHolder;
    }

    @Bean
    @ConditionalOnMissingBean
    public DistributedLockAspect distributedLockAspect() {
        return new DistributedLockAspect();
    }
}
