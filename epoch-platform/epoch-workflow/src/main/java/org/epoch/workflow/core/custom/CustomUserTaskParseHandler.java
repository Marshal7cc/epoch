package org.epoch.workflow.core.custom;

import org.epoch.workflow.core.custom.listener.AutoDelegateListener;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.UserTaskParseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/7/6
 * @desc:
 */
@Component
public class CustomUserTaskParseHandler extends UserTaskParseHandler {

    @Autowired
    private AutoDelegateListener autoDelegateListener;

    @Override
    protected void executeParse(BpmnParse bpmnParse, UserTask userTask) {
        super.executeParse(bpmnParse, userTask);

        //设置自动转交监听器
        ActivitiListener deliverListener = new ActivitiListener();
        deliverListener.setEvent(TaskListener.EVENTNAME_CREATE);
        deliverListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_INSTANCE);
        deliverListener.setInstance(autoDelegateListener);
        userTask.getTaskListeners().add(0, deliverListener);

    }
}
