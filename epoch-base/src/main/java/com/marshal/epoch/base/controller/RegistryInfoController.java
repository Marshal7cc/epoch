package com.marshal.epoch.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth: Marshal
 * @date: 2019/10/21
 * @desc:
 */
@RestController("/registry")
public class RegistryInfoController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/metaData")
    public Object getEurekaInstanceMetaData(@RequestParam String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

}
