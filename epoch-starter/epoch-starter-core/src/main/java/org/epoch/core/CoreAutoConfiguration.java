package org.epoch.core;

import org.epoch.core.base.BaseExceptionHandler;
import org.epoch.core.convert.config.ConvertWebMvcConfigurer;
import org.epoch.core.util.ApplicationContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marshal
 * @date 2020/5/30
 */
@Configuration
public class CoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BaseExceptionHandler exceptionHandler() {
        return new BaseExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }
}
