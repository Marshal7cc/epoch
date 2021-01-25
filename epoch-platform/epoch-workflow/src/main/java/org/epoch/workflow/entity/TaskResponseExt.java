package org.epoch.workflow.entity;


import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.activiti.rest.service.api.history.HistoricTaskInstanceResponse;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceResponse;
import org.activiti.rest.service.api.runtime.task.TaskResponse;

/**
 * @auth: Marshal
 * @date: 2019/2/8
 * @desc: 任务扩展
 */
public class TaskResponseExt extends TaskResponse {

    private String processName;
    private String startUserId;
    private String startUserName;
    private List<HistoricTaskInstanceResponse> historicTaskList = new ArrayList<>();
    private List<RestVariable> executionVariables;
    private ProcessInstanceResponse processInstance;

    public TaskResponseExt(Task task) {
        super(task);
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getStartUserName() {
        return startUserName;
    }

    public void setStartUserName(String startUserName) {
        this.startUserName = startUserName;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public List<HistoricTaskInstanceResponse> getHistoricTaskList() {
        return historicTaskList;
    }

    public void setHistoricTaskList(List<HistoricTaskInstanceResponse> historicTaskList) {
        this.historicTaskList = historicTaskList;
    }

    public List<RestVariable> getExecutionVariables() {
        return executionVariables;
    }

    public void setExecutionVariables(List<RestVariable> executionVariables) {
        this.executionVariables = executionVariables;
    }

    public ProcessInstanceResponse getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceResponse processInstance) {
        this.processInstance = processInstance;
    }
}
