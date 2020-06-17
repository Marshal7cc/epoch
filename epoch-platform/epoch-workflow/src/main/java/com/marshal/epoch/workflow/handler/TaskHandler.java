//package com.marshal.epoch.workflow.handler;
//
//import com.marshal.epoch.core.component.SessionContext;
//import com.marshal.epoch.workflow.entity.TaskActionRequestExt;
//import com.marshal.epoch.workflow.exception.TaskHandleException;
//import org.activiti.engine.task.Task;
//
///**
// * @auth: Marshal
// * @date: 2019/7/4
// * @desc: 工作流任务处理器
// */
//public interface TaskHandler {
//
//    /**
//     * 任务处理器唯一标识，根据任务处理行为区分
//     * 目前系统中的处理行为如下:
//     * 1.complete  同意/拒绝
//     * 2.delegate  转交(不同于工作流中的委托delegate)
//     * 3.resolve   被委托人处理(同意/拒绝)
//     * 4.addSign   加签
//     * 5.jump      跳转
//     * <p>
//     * 以上complete和resolve以approveResult作为action
//     *
//     * @return
//     */
//    String getAction();
//
//    /**
//     * 任务处理器前置处理
//     * 一般是一些前置校验
//     * 默认不做处理，可通过继承实现自己的前置逻辑
//     *
//     * @throws TaskHandleException
//     */
//    default void preHandle(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException {
//
//    }
//
//    /**
//     * 任务处理器实际处理过程
//     *
//     * @throws TaskHandleException
//     */
//    void process(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException;
//
//    /**
//     * 任务处理器后置处理
//     *
//     * @throws TaskHandleException
//     */
//    default void postHandle(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException {
//
//    }
//
//    /**
//     * 任务处理外观模板方法
//     *
//     * @throws TaskHandleException
//     */
//    default void handle(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException {
//
//        preHandle(sessionContext, taskEntity, actionRequest);
//
//        process(sessionContext, taskEntity, actionRequest);
//
//        postHandle(sessionContext, taskEntity, actionRequest);
//
//    }
//}
