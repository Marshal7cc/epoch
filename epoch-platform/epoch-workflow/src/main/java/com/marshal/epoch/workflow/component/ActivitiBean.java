package com.marshal.epoch.workflow.component;

/**
 * 实现该接口的类，将可以在 activiti 流程中调用
 *
 * @author Marshal
 */
public interface ActivitiBean {

    /**
     * 指定代理表达式的名称，若不指定则返回spring默认的name
     *
     * @return delegateExpression name
     */
    default String getBeanName() {
        return null;
    }

}
