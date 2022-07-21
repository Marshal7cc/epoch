package org.epoch.core.autoconfigure;

import org.epoch.core.util.ApplicationContextHelper;
import org.epoch.core.util.BaseConverter;
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
    public BaseConverter baseConverter() {
        return new BaseConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHelper applicationContextHolder() {
        return new ApplicationContextHelper();
    }
}
