package com.marshal.epoch.workflow.core.custom.behavior;

import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;

/**
 * <p>名称:CustomUserTaskActivityBehavior</p>
 * <p>描述:</p>
 *
 * @author Marshal Yuan
 * @since 2020/6/6
 */
public class CustomUserTaskActivityBehavior extends UserTaskActivityBehavior {

    public CustomUserTaskActivityBehavior(UserTask userTask) {
        super(userTask);
    }

    @Override
    public void execute(DelegateExecution execution) {
        super.execute(execution);
    }
}
