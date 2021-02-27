package org.epoch.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Marshal
 * @date 2020/5/31
 */
@Configuration
public class SwaggerConfiguration {

    private static final String DEFAULT_SCAN_PACKAGE = "org.epoch";
    private static final String DEFAULT_DESCRIPTION = "epoch平台api文档";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(DEFAULT_SCAN_PACKAGE)).paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(DEFAULT_DESCRIPTION).description(DEFAULT_DESCRIPTION).version("1.0").build();
    }
}
