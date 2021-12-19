package org.epoch.starter.web.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Marshal
 * @date 2020/5/31
 */
@Configuration
public class SwaggerConfiguration {

    private static final String DEFAULT_SCAN_PACKAGE = "org.epoch";
    private static final String DEFAULT_DESCRIPTION = "epoch平台接口文档";

    @Value("${security.oauth2.client.scope:}")
    private List<String> scopes;
    @Value("${security.oauth2.client.clientId:}")
    private String clientId;
    @Value("${security.oauth2.client.clientSecret:}")
    private String clientSecret;
    @Value("${security.oauth2.client.accessTokenUri:}")
    private String accessTokenUri;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(DEFAULT_SCAN_PACKAGE)).paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchemes()))
                .securityContexts(Collections.singletonList(securityContexts()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(DEFAULT_DESCRIPTION).description(DEFAULT_DESCRIPTION).version("1.0").build();
    }

    private SecurityScheme securitySchemes() {
        // 认证方式使用密码模式
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);

        return new OAuthBuilder()
                .name("Authorization")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    private SecurityContext securityContexts() {
        // 设置 swagger2 认证的安全上下文
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("Authorization", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes() {
        if (scopes.isEmpty()) {
            return new AuthorizationScope[0];
        }
        return scopes.stream().map(o -> new AuthorizationScope(o, null)).toArray(AuthorizationScope[]::new);
    }
}
