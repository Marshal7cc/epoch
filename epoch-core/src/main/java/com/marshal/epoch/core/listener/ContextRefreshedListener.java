package com.marshal.epoch.core.listener;

import org.springframework.context.ApplicationContext;

/**
 * spring context初始化完毕后会调用contextInitialized方法
 * <p>
 * 继承该类，可自定义初始化操作
 */
public interface ContextRefreshedListener {

    void contextInitialized(ApplicationContext applicationContext);

}
