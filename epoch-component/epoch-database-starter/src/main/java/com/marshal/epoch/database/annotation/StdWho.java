package com.marshal.epoch.database.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Marshal
 * @description 当且仅当实体类引入@StdWho注解并且enabled为false时，不记录操作人/操作时间
 * @since 2020/4/20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StdWho {

    boolean enabled() default true;

}
