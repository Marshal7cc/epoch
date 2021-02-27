package org.epoch.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marshal
 * @date 2020/4/23
 */
@Data
@ConfigurationProperties(prefix = "epoch.security")
public class SecurityProperty {
    private boolean enableAuth;
    private String[] whiteList;

}
