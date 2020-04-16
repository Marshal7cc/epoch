package com.marshal.epoch.auth.config;

import com.marshal.epoch.auth.constants.Oauth2EndpointConstant;
import com.marshal.epoch.auth.filter.ValidateCodeFilter;
import com.marshal.epoch.auth.service.impl.EpochUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @auth: Marshal
 * @date: 2020/1/15
 * @desc:
 */
@Order(2)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements Oauth2EndpointConstant {

    @Autowired
    private EpochUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers(OAUTH_ALL, LOGIN)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN).permitAll()
                .antMatchers(OAUTH_AUTHORIZE).authenticated()
                .and()
                .csrf().disable()
                //为了oauth2 授权码模式而追加 ==》/oauth/authorize接口必须走security认证,也就是必须登录
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}

