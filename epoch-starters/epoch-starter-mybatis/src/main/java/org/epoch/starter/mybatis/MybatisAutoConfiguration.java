package org.epoch.starter.mybatis;

import java.util.Optional;

import org.epoch.starter.mybatis.helper.NoneSnowflakeMetaProvider;
import org.epoch.starter.mybatis.helper.RedisSnowflakeMetaProvider;
import org.epoch.starter.mybatis.helper.SnowflakeHelper;
import org.epoch.starter.mybatis.helper.SnowflakeMetaProvider;
import org.epoch.starter.mybatis.properties.MybatisProperties;
import org.epoch.starter.redis.helper.RedisHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Marshal
 * @date 2020/4/20
 */
@MapperScan("org.epoch.**.mapper")
@ComponentScan(basePackages = "org.epoch.mybatis.**")
@EnableConfigurationProperties({MybatisProperties.class})
public class MybatisAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(SnowflakeHelper.class)
    @ConditionalOnProperty(name = "epoch.mybatis.key-generator", havingValue = "snowflake", matchIfMissing = true)
    public SnowflakeHelper snowflakeHelper(MybatisProperties properties,
                                           ApplicationContext context,
                                           @Value("${spring.application.name}") String serviceName,
                                           RedisHelper redisHelper) {
        SnowflakeMetaProvider snowflakeMetaProvider;
        if (MybatisProperties.SnowflakeProperties.MetaProvider.redis.equals(properties.getSnowflake().getMetaProvider())) {
            snowflakeMetaProvider = new RedisSnowflakeMetaProvider(serviceName,
                    properties.getSnowflake(),
                    properties.getSnowflake().getBitDataCenterId(),
                    properties.getSnowflake().getBitWorkerId(),
                    redisHelper);
        } else {
            snowflakeMetaProvider = new NoneSnowflakeMetaProvider(properties.getSnowflake().getBitDataCenterId(),
                    properties.getSnowflake().getBitWorkerId());
        }
        return new SnowflakeHelper(properties.getSnowflake().getStartTimestamp(),
                Optional.ofNullable(properties.getSnowflake().getDataCenterId())
                        .orElseGet(() -> snowflakeMetaProvider.dataCenterId(context)),
                Optional.ofNullable(properties.getSnowflake().getWorkerId())
                        .orElseGet(() -> snowflakeMetaProvider.workerId(context)),
                properties.getSnowflake().getBitTimestamp(),
                properties.getSnowflake().getBitDataCenterId(),
                properties.getSnowflake().getBitWorkerId(),
                properties.getSnowflake().getBitSequence());
    }
}
