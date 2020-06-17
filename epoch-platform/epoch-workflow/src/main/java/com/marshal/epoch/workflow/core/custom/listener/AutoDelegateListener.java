package com.marshal.epoch.workflow.core.custom.listener;

import java.util.Date;
import java.util.List;

import com.marshal.epoch.core.listener.ContextRefreshedListener;
import com.marshal.epoch.workflow.constant.ActivitiConstant;
import com.marshal.epoch.workflow.entity.ActCusDeliver;
import com.marshal.epoch.workflow.service.ActCusDeliverService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/7/6
 * @desc: 流程自动转交监听器
 */
@Component
public class AutoDelegateListener implements TaskListener, ContextRefreshedListener, ActivitiConstant {

    private TaskService taskService;

    @Autowired
    private ActCusDeliverService actCusDeliverService;

    @Override
    public void notify(DelegateTask delegateTask) {
        TaskEntity task = (TaskEntity) delegateTask;

        String employeeCode = task.getAssignee();

        String autoDeliverCode = getAutoDeliverCode(employeeCode);

        if (StringUtils.isNotEmpty(autoDeliverCode)) {
            String taskId = task.getId();

            taskService.addComment(taskId, task.getProcessInstanceId(), APPROVE_ACTION, ACTION_AUTO_DELEGATE);
            taskService.addComment(taskId, task.getProcessInstanceId(), APPROVE_COMMENT, task.getAssignee() + " 转交给 " + autoDeliverCode);
            taskService.setAssignee(taskId, autoDeliverCode);

        }
    }

    private String getAutoDeliverCode(String employeeCode) {
        ActCusDeliver condition = new ActCusDeliver();
        condition.setEmployeeCode(employeeCode);

        List<ActCusDeliver> actCusDeliverList = actCusDeliverService.select(condition);

        if (actCusDeliverList.size() == 0) {
            return null;
        }

        ActCusDeliver actCusDeliver = actCusDeliverList.get(0);

        Date deliverStartDate = actCusDeliver.getDeliverStartDate();
        Date deliverEndDate = actCusDeliver.getDeliverEndDate();

        //未设置转交开始时间或开始时间晚于当前时间
        if (deliverStartDate == null || deliverStartDate.getTime() > System.currentTimeMillis()) {
            return null;
        }

        //转交结束时间早于当前时间
        if (deliverEndDate.getTime() < System.currentTimeMillis()) {
            return null;
        }

        return actCusDeliver.getDeliverCode();

    }

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        taskService = applicationContext.getBean(TaskService.class);
    }
}
