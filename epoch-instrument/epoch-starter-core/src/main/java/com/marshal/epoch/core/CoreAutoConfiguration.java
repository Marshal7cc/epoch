package com.marshal.epoch.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marshal.epoch.core.base.BaseExceptionHandler;
import com.marshal.epoch.core.util.ApplicationContextHolder;

/**
 * @author Marshal Yuan
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
