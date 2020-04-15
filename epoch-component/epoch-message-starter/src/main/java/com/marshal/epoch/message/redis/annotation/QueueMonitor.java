/*
 * #{copyright}#
 */

package com.marshal.epoch.message.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueueMonitor {
    String queue() default "";

    /**
     * default empty,auto detect depends on object type.
     * <p>
     * ITopicMessageListener:onTopicMessage<br>
     * OTHERS:onMessage
     */
    String method() default "onMessage";

}
