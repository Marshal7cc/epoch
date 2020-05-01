package com.marshal.epoch.security.config;

import com.marshal.epoch.security.properties.SecurityProperty;
import com.marshal.epoch.security.voter.PermissionVoter;
import com.marshal.epoch.common.util.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2020/4/23
 * @desc:
 */
@ComponentScan(basePackages = "com.marshal.epoch.security")
@EnableConfigurationProperties(SecurityAutoConfiguration.class)
public class SecurityAutoConfiguration {

    @Autowired
    private SecurityProperty securityProperty;

    /**
     * 1.未登录
     * permitAll   --走accessDecisionManager,只要不返回ACCESS_ABSTAIN都是401
     * 未permitAll --不走,直接401
     * 2.登录
     * permitAll   --走,未通过认证返回403
     * 未permitAll --走,未通过认证返回403
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public UnanimousBased accessDecisionManager() {
        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        //webExpressionVoter必须指定，否则报错,且在oauth2下需指定expHandler
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
        voters.add(webExpressionVoter);
        voters.add(new RoleVoter());

        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        Map<String, PermissionVoter> customVoters = applicationContext.getBeansOfType(PermissionVoter.class);
        if (customVoters != null) {
            customVoters.forEach((k, v) -> {
                voters.add(v);
            });
        }

        return new UnanimousBased(voters);
    }

}
