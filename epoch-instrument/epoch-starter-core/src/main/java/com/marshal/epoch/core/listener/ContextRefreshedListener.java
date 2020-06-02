package com.marshal.epoch.core.listener;

import org.springframework.context.ApplicationContext;


/**
 * spring context初始化完毕后会调用contextInitialized方法
 * <p>
 * 继承该类，可自定义初始化操作
 *
 * @author Marshal
 * @date : 2019/1/13
 */
public interface ContextRefreshedListener {

    /**
     * 容器初始化操作
     *
     * @param applicationContext
     */
    void contextInitialized(ApplicationContext applicationContext);

}
