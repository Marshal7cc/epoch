package org.epoch.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import org.epoch.lock.enums.LockType;


/**
 * Annotation for lock.
 *
 * @author Marshal
 * @date 2021/12/7
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Lock {

    /**
     * Redisson lock name.
     */
    String name() default "";

    /**
     * LockType.
     */
    LockType lockType() default LockType.REENTRANT;

    /**
     * The time to wait for the lock.
     */
    long waitTime() default 30L;

    /**
     * The time to release lock after holding the lock.
     * While the value is -1,it won't release the lock actually because of watch dog.
     */
    long leaseTime() default -1L;

    /**
     * The time unit of the timeout argument.
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * Custom business key.
     * Disable while @name have value.
     * Redisson lock name => fullClassName.methodName.-key1-key2-key3...
     */
    String[] keys() default {};

    /**
     * Error message while getting lock fail.
     *
     * @return
     */
    String errorMessage() default "";
}
