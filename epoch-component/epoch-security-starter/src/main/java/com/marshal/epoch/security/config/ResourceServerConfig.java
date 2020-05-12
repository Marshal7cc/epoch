package com.marshal.epoch.security.config;

import com.marshal.epoch.security.handler.EpochAccessDeniedHandler;
import com.marshal.epoch.security.handler.EpochAuthenticationEntryPoint;
import com.marshal.epoch.security.properties.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @auth: Marshal
 * @date: 2020/1/15
 * @desc: 资源服务器认证配置
 */
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperty.class)
@ComponentScan(basePackages = "com.marshal.epoch.security")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperty securityProperty;

    @Autowired
    private AccessDecisionManager accessDecisionManager;

    @Autowired
    private EpochAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private EpochAuthenticationEntryPoint exceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/**");

        //白名单
        String[] whiteList = securityProperty.getWhiteList();
        if (whiteList != null) {
            http.authorizeRequests().antMatchers(whiteList).permitAll();
        }

        http.authorizeRequests()
                .antMatchers("/**").authenticated()
                .accessDecisionManager(accessDecisionManager)
                .and().httpBasic();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}

