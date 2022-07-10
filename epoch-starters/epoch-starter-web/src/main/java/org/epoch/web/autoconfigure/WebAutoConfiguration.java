package org.epoch.web.autoconfigure;

import org.epoch.web.base.BaseExceptionHandler;
import org.epoch.web.config.SwaggerConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Marshal
 * @date 2020/5/31
 */
@Configuration
@Import(value = SwaggerConfiguration.class)
public class WebAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.core", name = {"default-exception-handler-enable"}, havingValue = "true", matchIfMissing = true)
    public BaseExceptionHandler exceptionHandler() {
        return new BaseExceptionHandler();
    }
}
