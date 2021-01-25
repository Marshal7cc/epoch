package org.epoch.swagger.properties;

import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;

/**
 * <p>name:SwaggerProperties</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/16
 */
@Data
@Component
@ConfigurationProperties(prefix = "epoch.swagger")
public class SwaggerResourceProperties {
    private Map<String, SwaggerResource> resources;
}
