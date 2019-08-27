package com.marshal.epoch.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auth: Marshal
 * @date: 2019/8/25
 * @desc: 基础服务应用, 包括用户管理、角色管理、组织架构
 */
@ComponentScan("com.marshal.epoch.**")
@EnableEurekaClient
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

}
