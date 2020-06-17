package com.marshal.epoch.workflow.exception;

/**
 * @auth: Marshal
 * @date: 2019/4/6
 * @desc: 处理工作流task的异常
 */
public class TaskHandleException extends Exception {

    public static final String UNKNOWN_OPERATOR = "未知的行为!";
    public static final String DELEGATE_NO_ASSIGNEE = "未指定转交对象!";
    public static final String DELEGATE_TO_OWNER = "不可以转交给自己!";
    public static final String COMPLETE_TASK_NEED_ASSIGNEE_OR_ADMIN = "只有当前审批人/管理员才能完成此任务!";

    public TaskHandleException(String message) {
        super(message);
    }

}
