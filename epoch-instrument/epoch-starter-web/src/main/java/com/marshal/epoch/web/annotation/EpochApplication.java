package com.marshal.epoch.web.annotation;

import java.lang.annotation.*;

import com.marshal.epoch.security.annotation.EnableAuth;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * web项目启动注解
 *
 * @author Marshal Yuan
 * @date 2020/5/31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableAuth
@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
public @interface EpochApplication {
}
