package org.epoch.core.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epoch.core.util.ApplicationContextHelper;
import org.epoch.core.util.GenericTypeConverter;
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
    public GenericTypeConverter baseConverter(ObjectMapper objectMapper) {
        return new GenericTypeConverter(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHelper applicationContextHolder() {
        return new ApplicationContextHelper();
    }
}
