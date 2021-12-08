package org.epoch.starter.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate that the parameter with this annotation is a part of Lock Identifier.
 * Usage:
 * 1.lockDemo(@LockKey String id)
 * 2.lockDemo(@LockKey("userId") User user)
 *
 * @author Marshal
 * @date 2021/12/7
 * @see Lock
 */
@Target(value = {ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LockKey {
    String value() default "";
}
