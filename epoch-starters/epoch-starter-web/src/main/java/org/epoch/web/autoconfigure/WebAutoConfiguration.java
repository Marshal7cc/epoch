package org.epoch.web.autoconfigure;

import org.epoch.security.domain.support.DefaultAuditorAware;
import org.epoch.web.advisor.BaseExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;

/**
 * @author Marshal
 * @date 2020/5/31
 */
@Configuration
@Import(value = SwaggerAutoConfiguration.class)
public class WebAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AuditorAware<String> auditorAware() {
        return new DefaultAuditorAware();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.core", name = {"default-exception-handler-enable"}, havingValue = "true", matchIfMissing = true)
    public BaseExceptionHandler exceptionHandler() {
        return new BaseExceptionHandler();
    }
}
