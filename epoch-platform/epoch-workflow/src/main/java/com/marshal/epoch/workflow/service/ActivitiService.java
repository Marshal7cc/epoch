package com.marshal.epoch.workflow.service;

import java.util.List;

import com.marshal.epoch.workflow.entity.HistoricProcessInstanceResponseExt;
import com.marshal.epoch.workflow.entity.TaskActionRequestExt;
import com.marshal.epoch.workflow.entity.TaskResponseExt;
import com.marshal.epoch.workflow.exception.TaskHandleException;

public interface ActivitiService {

    /**
     * 工作流任务处理
     *
     * @param taskId            task id
     * @param taskActionRequest 任务处理请求信息
     * @param isAdmin           是否为管理员
     * @throws TaskHandleException
     */
    void handleTask(String taskId, TaskActionRequestExt taskActionRequest, boolean isAdmin)
            throws TaskHandleException, IllegalArgumentException;

    /**
     * 代办列表
     *
     * @param taskList
     * @return
     */
    List<TaskResponseExt> getTaskList(List<TaskResponseExt> taskList);

    /**
     * 查看任务详情
     *
     * @param task
     * @return
     */
    TaskResponseExt getTaskDetail(TaskResponseExt task);

    /**
     * 流程监控/历史流程列表
     *
     * @param historicProcessInstanceList
     * @return
     */
    List<HistoricProcessInstanceResponseExt> getHistoricProcessInstanceList(List<HistoricProcessInstanceResponseExt> historicProcessInstanceList);

    /**
     * 流程监控/历史流程详情
     *
     * @param processInstanceId
     * @return
     */
    HistoricProcessInstanceResponseExt getProcessInstanceDetail(String processInstanceId);

}
