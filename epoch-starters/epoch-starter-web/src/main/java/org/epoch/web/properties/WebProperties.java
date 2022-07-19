package org.epoch.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marshal
 * @since 2022/7/19
 */
@Data
@ConfigurationProperties(prefix = "epoch.web")
public class WebProperties {
    private Boolean defaultExceptionHandleEnable;
}
