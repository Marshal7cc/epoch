package org.epoch.starter.mybatis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marshal
 * @date 2021/3/4
 */
@Data
@ConfigurationProperties(prefix = "epoch.mybatis")
public class MybatisProperties {

}
