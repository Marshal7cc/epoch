package org.epoch.core.autoconfigure;

import org.epoch.core.properties.CoreProperties;
import org.epoch.core.util.ApplicationContextHelper;
import org.epoch.core.util.BaseConverter;
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
    public BaseConverter typeConverter() {
        return new BaseConverter();
    }

    @Bean
    public ApplicationContextHelper applicationContextHolder() {
        return new ApplicationContextHelper();
    }
}
