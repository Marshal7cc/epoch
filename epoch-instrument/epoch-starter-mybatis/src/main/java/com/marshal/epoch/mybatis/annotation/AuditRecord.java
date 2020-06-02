package com.marshal.epoch.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 当且仅当实体类引入@AuditRecord注解并且enabled为false时，不记录操作人/操作时间
 * </p>
 *
 * @author Marshal
 * @date 2020/4/20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditRecord {

    boolean enabled() default true;

}
