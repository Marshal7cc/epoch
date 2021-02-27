package org.epoch.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.epoch.security.handler.EpochAccessDeniedHandler;
import org.epoch.security.handler.EpochAuthenticationEntryPoint;
import org.epoch.security.properties.SecurityProperty;
import org.epoch.security.voter.PermissionVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Marshal
 * @date 2020/4/23
 */
@Configuration
@EnableConfigurationProperties(SecurityProperty.class)
public class SecurityAutoConfiguration {

    @Configuration
    @ConditionalOnProperty(prefix = "epoch.security", name = {"enable-auth"}, havingValue = "true", matchIfMissing = true)
    @Import({ManagementWebSecurityAutoConfiguration.class,
            org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,})
    public class AuthEnabledConfiguration {
        @Autowired
        private SecurityProperty securityProperty;

        @Autowired
        private ApplicationContext applicationContext;

        @Bean
        @ConditionalOnMissingBean
        public EpochAccessDeniedHandler accessDeniedHandler() {
            return new EpochAccessDeniedHandler();
        }

        /**
         * todo: fix me by update version or other way
         * 暂时解决异常时自动转发至/error导致的401问题
         *
         * @return OAuth2ClientContext
         */
        @Bean
        @Primary
        public OAuth2ClientContext oAuth2ClientContext() {
            return new DefaultOAuth2ClientContext();
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

    /**
     * swagger allow cors configuration
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/v2/api-docs", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}
