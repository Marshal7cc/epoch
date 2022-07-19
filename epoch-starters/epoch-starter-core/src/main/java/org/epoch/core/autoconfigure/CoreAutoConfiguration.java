package org.epoch.core.autoconfigure;

import org.epoch.core.properties.CoreProperties;
import org.epoch.core.util.ApplicationContextHolder;
import org.epoch.core.util.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marshal
 * @date 2020/5/30
 */
@Configuration
@EnableConfigurationProperties({CoreProperties.class})
public class CoreAutoConfiguration {
    @Autowired
    private CoreProperties coreProperties;

    @Bean
    public TypeConverter typeConverter() {
        return new TypeConverter();
    }

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }
}
