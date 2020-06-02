package com.marshal.epoch.lock.annotation;

import java.lang.annotation.*;

/**
 * <p>DistributedLock</p>
 * <pre>
 *     分布式锁注解
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/5/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DistributedLock {

    /**
     * 锁的key
     *
     * @return
     */
    String key();

    /**
     * 获取锁的最大等待时间
     *
     * @return
     */
    long timeout() default 4000L;

    /**
     * 锁过期时间
     *
     * @return
     */
    long ttl() default 5000;

    /**
     * 是否采用锁的异步执行方式(异步拿锁，同步阻塞)
     *
     * @return
     */
    boolean async() default false;

    /**
     * 是否公平锁，默认为非公平锁
     *
     * @return
     */
    boolean fair() default false;

}
