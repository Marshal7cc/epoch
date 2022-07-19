package org.epoch.core.proxy;

import org.springframework.aop.framework.AopContext;

/**
 * 获取代理对象 self.
 *
 * @author Marshal
 * @date 2020/5/30
 */
public interface AopProxy<T> {

    /**
     * 取得当前对象的代理.
     *
     * @return 代理对象, 如果未被代理, 则抛出 IllegalStateException
     */
    @SuppressWarnings("unchecked")
    default T self() {
        return (T) AopContext.currentProxy();
    }
}
