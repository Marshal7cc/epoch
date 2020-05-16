package com.marshal.epoch.lock.propertis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auth: Marshal
 * @date: 2020/4/17
 * @desc: redis连接配置
 */
@Data
@ConfigurationProperties(prefix = "epoch.distributed-lock.jedis")
public class JedisPoolProperty {

    private String host = "localhost";

    private String password;

    private int database = 7;

    private Integer port = 6379;

    private Integer maxTotal = 100;

    private Integer maxIdle = 8;

    private Integer maxWaitMillis = 1000 * 100;
}
