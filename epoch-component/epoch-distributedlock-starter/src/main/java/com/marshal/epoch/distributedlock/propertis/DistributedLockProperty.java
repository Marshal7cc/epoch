package com.marshal.epoch.distributedlock.propertis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @auth: Marshal
 * @date: 2020/4/17
 * @desc:
 */
@Data
@ConfigurationProperties(prefix = "epoch.distributed-lock")
@EnableConfigurationProperties({JedisPoolProperty.class})
public class DistributedLockProperty {

    private String type;

    private JedisPoolProperty jedis;
}
