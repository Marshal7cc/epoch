package com.marshal.epoch.security.annotation;

import com.marshal.epoch.security.config.SecurityAutoConfiguration;
import com.marshal.epoch.security.config.ResourceServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 * 资源服务器权限注解
 * <p/>
 * <pre>
 *     an annotation mark application as an authorization server
 * </pre>
 *
 * @author Marshal
 * @date : 2020/1/15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ResourceServerConfiguration.class, SecurityAutoConfiguration.class})
public @interface EpochResourceServer {
}
