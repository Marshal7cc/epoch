//package org.epoch.workflow.handler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.epoch.core.listener.ContextRefreshedListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
///**
// * @auth: Marshal
// * @date: 2019/7/4
// * @desc: Prototype - provide all kinds of task handler
// */
//@Component
//@Slf4j
//public class TaskHandlerProvider implements ContextRefreshedListener {
//
//    private static Map<String, TaskHandler> handlerMap = new HashMap<>();
//
//    /**
//     * 根据action获取处理器
//     *
//     * @param action
//     * @return
//     */
//    public static TaskHandler getTaskHandler(String action) {
//        return handlerMap.get(action);
//    }
//
//    /**
//     * 初始化所有任务处理器
//     *
//     * @param applicationContext
//     */
//    @Override
//    public void contextInitialized(ApplicationContext applicationContext) {
//
//        Map<String, TaskHandler> beans = applicationContext.getBeansOfType(TaskHandler.class);
//
//        beans.forEach((k, v) -> {
//            if (v.getAction() == null) {
//                log.error("action can not be null");
//            } else {
//                handlerMap.put(v.getAction(), v);
//            }
//        });
//
//    }
//
//}
