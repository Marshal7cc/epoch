package org.epoch.workflow.core.custom.listener;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.epoch.workflow.util.WorkflowUtils;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 工作流创建消息监听
 *
 * @author Marshal Yuan
 * @since 2020/6/6
 */
@Component
public class NotificationListener implements TaskListener {

    private Collection<IUserTaskNotifier> notifiers = Collections.emptyList();

    @Override
    public void notify(DelegateTask delegateTask) {
        if ("create".equals(delegateTask.getEventName())) {
            TaskEntity task = (TaskEntity) delegateTask;
            String assignee = task.getAssignee();
            List<FormProperty> formProperties = ((UserTask) task.getExecution().getCurrentFlowElement()).getFormProperties();
            FormProperty notifiType = WorkflowUtils.getFormPropertyById(formProperties, "TASK_NOTIFY_TYPE");
            if (notifiType != null) {
                List<String> types = (List) notifiType.getFormValues().stream().map((t) -> {
                    return t.getId();
                }).collect(Collectors.toList());
                if (StringUtils.isNotEmpty(assignee)) {
//                    UserEntity user = (UserEntity)this.userDataManager.findById(assignee);
//                    this.sendNotification(user, task, types);
                }
            }
        }
    }

    public void sendNotification(UserEntity user, TaskEntity task, List<String> types) {
        if (user == null) {
            throw new ActivitiException("Employee Not Found.");
            //todo ：do notify
        }
    }
}
