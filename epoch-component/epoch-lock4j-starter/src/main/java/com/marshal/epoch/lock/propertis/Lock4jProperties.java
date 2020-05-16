package com.marshal.epoch.lock.propertis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @auth: Marshal
 * @date: 2020/4/17
 * @desc:
 */
@ConfigurationProperties(prefix = "epoch.lock4j")
@EnableConfigurationProperties({RedissonProperties.class})
public class Lock4jProperties {

    private String type = "redisson";

    @Autowired
    private RedissonProperties redissonProperties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RedissonProperties getRedissonProperties() {
        return redissonProperties;
    }

    public void setRedissonProperties(RedissonProperties redissonProperties) {
        this.redissonProperties = redissonProperties;
    }
}
