package com.marshal.epoch.auth.config;

import com.marshal.epoch.auth.constants.Oauth2EndpointConstant;
import com.marshal.epoch.auth.service.impl.EpochUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @auth: Marshal
 * @date: 2020/1/15
 * @desc:
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements Oauth2EndpointConstant {

    @Autowired
    private EpochUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers(OAUTH_ALL)
                .and()
                .authorizeRequests()
                .antMatchers(OAUTH_ALL).authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}

