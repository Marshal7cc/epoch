package com.marshal.epoch.web;

import com.marshal.epoch.web.config.SwaggerConfiguration;
import com.marshal.epoch.web.feign.EpochSpringMvcContract;
import feign.Contract;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Marshal Yuan
 * @date 2020/5/31
 */
@Configuration
@Import(value = SwaggerConfiguration.class)
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Contract feignContract() {
        return new EpochSpringMvcContract();
    }
}
