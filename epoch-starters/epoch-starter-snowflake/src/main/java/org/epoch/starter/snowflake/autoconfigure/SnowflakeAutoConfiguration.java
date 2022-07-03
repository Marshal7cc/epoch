package org.epoch.starter.snowflake.autoconfigure;

import java.util.Optional;

import org.epoch.starter.redis.helper.RedisHelper;
import org.epoch.starter.snowflake.helper.NoneSnowflakeMetaProvider;
import org.epoch.starter.snowflake.helper.RedisSnowflakeMetaProvider;
import org.epoch.starter.snowflake.helper.SnowflakeHelper;
import org.epoch.starter.snowflake.helper.SnowflakeMetaProvider;
import org.epoch.starter.snowflake.properties.SnowflakeProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Marshal
 * @since 2022/7/3
 */
@EnableConfigurationProperties(SnowflakeProperties.class)
public class SnowflakeAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(SnowflakeHelper.class)
    @ConditionalOnProperty(name = "epoch.mybatis.key-generator", havingValue = "snowflake", matchIfMissing = true)
    public SnowflakeHelper snowflakeHelper(SnowflakeProperties properties,
                                           ApplicationContext context,
                                           @Value("${spring.application.name}") String serviceName,
                                           RedisHelper redisHelper) {
        SnowflakeMetaProvider snowflakeMetaProvider;
        if (SnowflakeProperties.MetaProvider.redis.equals(properties.getMetaProvider())) {
            snowflakeMetaProvider = new RedisSnowflakeMetaProvider(serviceName, properties,
                    properties.getBitDataCenterId(),
                    properties.getBitWorkerId(),
                    redisHelper);
        } else {
            snowflakeMetaProvider = new NoneSnowflakeMetaProvider(properties.getBitDataCenterId(),
                    properties.getBitWorkerId());
        }
        return new SnowflakeHelper(properties.getStartTimestamp(),
                Optional.ofNullable(properties.getDataCenterId())
                        .orElseGet(() -> snowflakeMetaProvider.dataCenterId(context)),
                Optional.ofNullable(properties.getWorkerId())
                        .orElseGet(() -> snowflakeMetaProvider.workerId(context)),
                properties.getBitTimestamp(),
                properties.getBitDataCenterId(),
                properties.getBitWorkerId(),
                properties.getBitSequence());
    }
}
