package org.epoch.starter.web.autoconfigure;

import org.epoch.starter.web.config.SwaggerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Marshal
 * @date 2020/5/31
 */
@Configuration
@Import(value = SwaggerConfiguration.class)
public class WebAutoConfiguration {


}
