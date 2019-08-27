package com.marshal.epoch.core.security.config;

import com.marshal.epoch.core.security.component.JwtAuthenticationProvider;
import com.marshal.epoch.core.security.component.JwtHeadFilter;
import com.marshal.epoch.core.security.component.JwtLoginFilter;
import com.marshal.epoch.core.security.voter.ResourceVoter;
import com.marshal.epoch.core.security.handler.CustomAuthenticationAccessDeniedHandler;
import com.marshal.epoch.core.security.handler.CustomAuthenticationFailureHandler;
import com.marshal.epoch.core.security.handler.CustomAuthenticationSuccessHandler;
import com.marshal.epoch.core.security.handler.CustomLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/11/28
 * @desc: spring security配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomLogoutHandler logoutHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtHeadFilter jwtHeadFilter;


    /**
     * 认证管理器,确定用户,角色及相应的权限
     *
     * @return
     */
    @Bean
    UnanimousBased accessDecisionManager() {
        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        //webExpressionVoter必须指定，否则报错
        voters.add(new WebExpressionVoter());
        voters.add(new RoleVoter());
        voters.add(new ResourceVoter());
        UnanimousBased accessDecisionManager = new UnanimousBased(voters);
        return accessDecisionManager;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码加密工具，注入即自动检测使用
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //登录过滤器
        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter();
        jwtLoginFilter.setAuthenticationManager(this.authenticationManagerBean());

        //登录成功和失败的操作

        jwtLoginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        jwtLoginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        http
                //身份验证入口,当需要登录却没登录时调用
                //具体为,当抛出AccessDeniedException异常时且当前是匿名用户时调用
                //匿名用户: 当过滤器链走到匿名过滤器(AnonymousAuthenticationFilter)时,
                //会进行判断SecurityContext是否有凭证(Authentication),若前面的过滤器都没有提供凭证,
                //匿名过滤器会给SecurityContext提供一个匿名的凭证(可以理解为用户名和权限为anonymous的Authentication),
                //这也是JwtHeadFilter发现请求头中没有jwtToken不作处理而直接进入下一个过滤器的原因
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("需要登陆");
        })

                //拒绝访问处理,当已登录,但权限不足时调用
                //抛出AccessDeniedException异常时且当不是匿名用户时调用
                .accessDeniedHandler(authenticationAccessDeniedHandler)
                .and()
                //将授权提供者注册到授权管理器中(AuthenticationManager)
                .authenticationProvider(jwtAuthenticationProvider)
                .addFilterAfter(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtHeadFilter, JwtLoginFilter.class)
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }
}
