package org.epoch.core.config;

/**
 * 配置器
 *
 * @author Marshal
 * @date 2020/5/8
 */
@FunctionalInterface
public interface Configurer<T> {

    /**
     * 配置param
     *
     * @param param
     */
    void configure(T param);

}
