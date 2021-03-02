package org.epoch.core;

import org.epoch.core.base.BaseExceptionHandler;
import org.epoch.core.endpoint.RefreshConfigEndpoint;
import org.epoch.core.properties.CoreProperties;
import org.epoch.core.util.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.refresh.ContextRefresher;
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
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.core", name = {"refresh-enable"}, havingValue = "true", matchIfMissing = true)
    public RefreshConfigEndpoint endpoint(ContextRefresher contextRefresher) {
        return new RefreshConfigEndpoint(contextRefresher);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.core", name = {"default-exception-handler-enable"}, havingValue = "true", matchIfMissing = true)
    public BaseExceptionHandler exceptionHandler() {
        return new BaseExceptionHandler();
    }
}
