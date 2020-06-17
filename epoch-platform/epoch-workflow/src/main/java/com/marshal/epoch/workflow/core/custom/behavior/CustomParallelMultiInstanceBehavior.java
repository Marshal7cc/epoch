package com.marshal.epoch.workflow.core.custom.behavior;

import org.activiti.bpmn.model.Activity;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;

/**
 * @auth: Marshal
 * @date: 2019/7/7
 * @desc: 自定义并行多实例行为
 */
public class CustomParallelMultiInstanceBehavior extends ParallelMultiInstanceBehavior {

    public CustomParallelMultiInstanceBehavior(Activity activity, AbstractBpmnActivityBehavior originalActivityBehavior) {
        super(activity, originalActivityBehavior);
    }

    @Override
    protected int createInstances(DelegateExecution execution) {
        return super.createInstances(execution);
        //todo fill custom behaviour

    }

    @Override
    public void leave(DelegateExecution execution) {
        super.leave(execution);
        //todo fill custom behaviour
    }
}
