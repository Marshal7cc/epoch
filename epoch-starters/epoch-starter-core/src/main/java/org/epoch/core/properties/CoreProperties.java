package org.epoch.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marshal
 * @date 2021/1/27
 */
@Data
@ConfigurationProperties(prefix = "epoch.core")
public class CoreProperties {
}
