package com.marshal.epoch.test;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Marshal
 * @date 2019/12/16
 * @desc
 */
@EnableSwagger2Doc
@ComponentScan(basePackages = "com.marshal.epoch")
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
