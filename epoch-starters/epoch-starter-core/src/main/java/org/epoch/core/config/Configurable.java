package org.epoch.core.config;

/**
 * 可配置
 *
 * @author Marshal
 * @date 2020/5/8
 */
public interface Configurable<T> {

    /**
     * 配置
     *
     * @param configurer
     */
    void configure(Configurer<T> configurer);

}
