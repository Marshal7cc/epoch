package org.epoch.workflow.core.custom.behavior;

import org.activiti.bpmn.model.Activity;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.SequentialMultiInstanceBehavior;

/**
 * @auth: Marshal
 * @date: 2019/7/7
 * @desc: 自定义并行多实例行为
 */
public class CustomSequentialMultiInstanceBehavior extends SequentialMultiInstanceBehavior {

    public CustomSequentialMultiInstanceBehavior(Activity activity, AbstractBpmnActivityBehavior innerActivityBehavior) {
        super(activity, innerActivityBehavior);
    }

    @Override
    protected int createInstances(DelegateExecution multiInstanceExecution) {
        return super.createInstances(multiInstanceExecution);
        //todo fill custom behaviour
    }

    @Override
    public void leave(DelegateExecution childExecution) {
        super.leave(childExecution);
        //todo fill custom behaviour
    }
}
