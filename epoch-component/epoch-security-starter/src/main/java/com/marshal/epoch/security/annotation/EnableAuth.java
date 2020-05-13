package com.marshal.epoch.security.annotation;

import com.marshal.epoch.security.config.ResourceServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>开启资源服务器权限注解<p/>
 * 当且仅当:
 * 1.引入epoch-security-starter依赖
 * 2.使用注解@EnableAuth
 * 时能够真正接入Epoch安全认证服务,否则相当于仅引入相关jar包
 *
 * @auth: Marshal
 * @date: 2020/1/15
 * @desc:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ResourceServerConfig.class)
public @interface EnableAuth {
}
