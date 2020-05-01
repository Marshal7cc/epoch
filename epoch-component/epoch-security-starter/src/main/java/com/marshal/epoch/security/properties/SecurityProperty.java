package com.marshal.epoch.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auth: Marshal
 * @date: 2020/4/23
 * @desc:
 */
@Data
@ConfigurationProperties(prefix = "epoch.security")
public class SecurityProperty {

    private String[] whiteList;

}
