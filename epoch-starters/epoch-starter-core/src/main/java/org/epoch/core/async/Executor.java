package org.epoch.core.async;

/**
 * 用于异步的函数接口
 *
 * @author Marshal
 * @date 2021/1/24
 */
@FunctionalInterface
public interface Executor {
    String execute();
}
