package org.epoch.lock;

import java.io.IOException;

import org.epoch.lock.aop.DistributedLockAspect;
import org.epoch.lock.component.RedisDistributedLocker;
import org.epoch.lock.component.redisson.RedissonClientHolder;
import org.epoch.lock.propertis.Lock4jProperties;
import org.epoch.lock.propertis.RedissonProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


/**
 * epoch锁(主要是分布式锁)
 *
 * @author Marshal
 * @date 2020/4/17
 */
@EnableConfigurationProperties({Lock4jProperties.class})
public class LockAutoConfiguration {

    @Autowired
    private Lock4jProperties lock4jProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.lock4j", name = {"type"}, havingValue = "redisson")
    RedissonClient redisson() throws IOException {
        RedissonProperties redissonProperties = lock4jProperties.getRedissonProperties();
        Config config = new Config();
        config.useSingleServer().setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                .setConnectTimeout(redissonProperties.getConnectTimeout())
                .setTimeout(redissonProperties.getTimeout())
                .setRetryAttempts(redissonProperties.getRetryAttempts())
                .setRetryInterval(redissonProperties.getRetryInterval())
                .setPassword(redissonProperties.getPassword()).setAddress(redissonProperties.getAddress())
                .setClientName(redissonProperties.getClientName())
                .setSubscriptionConnectionMinimumIdleSize(
                        redissonProperties.getSubscriptionConnectionMinimumIdleSize())
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
