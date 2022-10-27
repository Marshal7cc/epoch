package org.epoch.core.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epoch.core.util.ApplicationContextHelper;
import org.epoch.core.util.GenericTypeConverter;
import org.springframework.context.annotation.Bean;

/**
 * @author Marshal
 * @date 2020/5/30
 */
public class CoreAutoConfiguration {
    @Bean
    public ApplicationContextHelper applicationContextHolder() {
        return new ApplicationContextHelper();
    }

    @Bean
    public GenericTypeConverter baseConverter(ObjectMapper objectMapper) {
        return new GenericTypeConverter(objectMapper);
    }
}
