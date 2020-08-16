package com.marshal.epoch.swagger.config;

import java.util.*;

import com.marshal.epoch.swagger.properties.SwaggerResourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * <p>name:SwaggerProvider</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/16
 */
@Primary
@Configuration
@EnableConfigurationProperties(SwaggerResourceProperties.class)
class SwaggerProvider implements SwaggerResourcesProvider {

    @Autowired
    private SwaggerResourceProperties swaggerResourceProperties;

    @Override
    public List<SwaggerResource> get() {
        Map<String, SwaggerResource> resources = swaggerResourceProperties.getResources();
        if (resources == null) {
            return Collections.emptyList();
        }
        List<SwaggerResource> resourceList = new LinkedList<>();
        resources.forEach((k, v) -> {
            resourceList.add(v);
        });
        return resourceList;
    }
}
