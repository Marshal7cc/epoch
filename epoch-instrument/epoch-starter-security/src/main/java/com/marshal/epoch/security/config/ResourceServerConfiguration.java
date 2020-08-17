package com.marshal.epoch.security.config;

import com.marshal.epoch.security.constants.SecurityConstants;
import com.marshal.epoch.security.handler.EpochAccessDeniedHandler;
import com.marshal.epoch.security.handler.EpochAuthenticationEntryPoint;
import com.marshal.epoch.security.properties.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * 资源服务器认证配置
 *
 * @author Marshal
 * @date 2020/1/15
 */
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperty.class)
@ConditionalOnProperty(prefix = "epoch.security", name = {"enable-auth"}, havingValue = "true", matchIfMissing = false)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperty securityProperty;
    @Autowired
    private AccessDecisionManager accessDecisionManager;
    @Autowired
    private EpochAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CorsConfigurationSource corsConfigurationSource;
    @Autowired
    private EpochAuthenticationEntryPoint exceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource);
        http.requestMatchers().antMatchers("/**");
        // 资源服务器默认白名单
        http.authorizeRequests().antMatchers(SecurityConstants.RESOURCE_SERVER_DEFAULT_WHITE_LIST).permitAll();
        // 自定义白名单
        String[] whiteList = securityProperty.getWhiteList();
        if (securityProperty.getWhiteList() != null) {
            http.authorizeRequests().antMatchers(whiteList).permitAll();
        }

        http.authorizeRequests().antMatchers("/**").authenticated()
                .accessDecisionManager(accessDecisionManager)
                .and().httpBasic();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint).accessDeniedHandler(accessDeniedHandler);
    }
}

