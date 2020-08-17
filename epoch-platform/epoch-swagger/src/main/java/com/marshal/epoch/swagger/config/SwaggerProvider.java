package com.marshal.epoch.swagger.config;

import java.util.*;

import com.marshal.epoch.swagger.constants.SwaggerConstants;
import com.marshal.epoch.swagger.exception.ClientNotFountException;
import com.marshal.epoch.swagger.properties.SwaggerResourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
    private DiscoveryClient discoveryClient;
    @Autowired
    private SwaggerResourceProperties swaggerResourceProperties;

    @Override
    public List<SwaggerResource> get() {
        Map<String, SwaggerResource> resources = swaggerResourceProperties.getResources();
        if (resources == null) {
            return Collections.emptyList();
        }
        List<SwaggerResource> resourceList = new LinkedList<>();
        for (Map.Entry<String, SwaggerResource> resourceItem : resources.entrySet()) {
            ServiceInstance serviceInstance = getServiceInstance(resourceItem.getKey());
            SwaggerResource swaggerResource = resourceItem.getValue();
            swaggerResource.setUrl(serviceInstance.getUri().toString() + SwaggerConstants.API_URL);
            resourceList.add(swaggerResource);
        }
        return resourceList;
    }

    /**
     * 获取其中一个服务的instance
     *
     * @param applicationName
     * @return
     */
    private ServiceInstance getServiceInstance(String applicationName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
        Optional<ServiceInstance> serviceInstance = instances.stream().findFirst();
        if (!serviceInstance.isPresent()) {
            throw new ClientNotFountException(String.format("client:{} not found", applicationName));
        }
        return serviceInstance.get();
    }
}
