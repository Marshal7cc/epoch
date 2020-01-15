package com.marshal.epoch.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @auth: Marshal
 * @date: 2020/1/15
 * @desc:
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:epoch-security.properties"})
@ConfigurationProperties(prefix = "epoch.security")
public class EpochSecurityProperties {

    private String tokenStoreStrategy;

    private String clientDetailsStrategy;

    private String jwtTokenAccessKey;

    private String anonUrl;

}
