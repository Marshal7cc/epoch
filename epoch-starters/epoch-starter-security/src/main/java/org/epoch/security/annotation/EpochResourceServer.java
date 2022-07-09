package org.epoch.security.annotation;

import java.lang.annotation.*;

import org.epoch.security.autoconfigure.ResourceServerConfiguration;
import org.epoch.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

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
