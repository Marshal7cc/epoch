package com.marshal.epoch.core.listener;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 监听spring上下文事件
 *
 * @author Marshal
 * @date : 2019/1/13
 */
@Component
public class ApplicationEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 监听容器初始化完成事件
        if (applicationEvent instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            Map<String, ContextRefreshedListener> listeners =
                    applicationContext.getBeansOfType(ContextRefreshedListener.class);
            listeners.forEach((k, v) -> {
                v.contextInitialized(applicationContext);
            });
        }
    }
}
