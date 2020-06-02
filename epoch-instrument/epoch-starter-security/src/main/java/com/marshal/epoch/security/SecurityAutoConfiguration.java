package com.marshal.epoch.security;

import com.marshal.epoch.security.handler.EpochAccessDeniedHandler;
import com.marshal.epoch.security.handler.EpochAuthenticationEntryPoint;
import com.marshal.epoch.security.properties.SecurityProperty;
import com.marshal.epoch.security.voter.PermissionVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Marshal
 * @date 2020/4/23
 */
@EnableConfigurationProperties(SecurityProperty.class)
public class SecurityAutoConfiguration {

    @Autowired
    private SecurityProperty securityProperty;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public EpochAccessDeniedHandler accessDeniedHandler() {
        return new EpochAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public EpochAuthenticationEntryPoint authenticationEntryPoint() {
        return new EpochAuthenticationEntryPoint();
    }

    /**
     * 1.未登录 permitAll --走accessDecisionManager,只要不返回ACCESS_ABSTAIN都是401 未permitAll --不走,直接401 2.登录
     * permitAll --走,未通过认证返回403 未permitAll --走,未通过认证返回403
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public UnanimousBased accessDecisionManager() {
        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        // webExpressionVoter必须指定，否则报错,且在oauth2下需指定expHandler
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
        voters.add(webExpressionVoter);
        voters.add(new RoleVoter());

        Map<String, PermissionVoter> customVoters = applicationContext.getBeansOfType(PermissionVoter.class);
        if (customVoters != null) {
            customVoters.forEach((k, v) -> {
                voters.add(v);
            });
        }

        return new UnanimousBased(voters);
    }

}
