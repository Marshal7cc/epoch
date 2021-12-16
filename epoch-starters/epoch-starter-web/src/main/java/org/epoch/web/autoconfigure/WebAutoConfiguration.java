package org.epoch.web.autoconfigure;

import org.epoch.web.config.SwaggerConfiguration;
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
