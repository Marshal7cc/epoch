package com.marshal.epoch.workflow.entity;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.rest.service.api.history.HistoricProcessInstanceResponse;
import org.activiti.rest.service.api.history.HistoricTaskInstanceResponse;

/**
 * @auth: Marshal
 * @date: 2019/4/7
 * @desc:
 */
public class HistoricProcessInstanceResponseExt extends HistoricProcessInstanceResponse {

    public static final String endStatus = "END";
    public static final String suspendStatus = "SUSPEND";
    public static final String runningStatus = "RUNNING";


    private String processInstanceStatus;
    private String processDefinitionName;

    private String startUserName;
    private String currentTaskName;
    private String currentTaskId;
    private String currentAssign;

    private String formUrl;

    private List<HistoricTaskInstanceResponse> historicTaskList = new ArrayList<>();

    private String description;

    public HistoricProcessInstanceResponseExt(HistoricProcessInstance historicProcessInstance) {
        this.id = historicProcessInstance.getId();
        this.processDefinitionName = historicProcessInstance.getProcessDefinitionName();
        this.startTime = historicProcessInstance.getStartTime();
        this.endTime = historicProcessInstance.getEndTime();
        this.startUserId = historicProcessInstance.getStartUserId();
        this.businessKey = historicProcessInstance.getBusinessKey();
    }

    public static String getEndStatus() {
        return endStatus;
    }

    public static String getSuspendStatus() {
        return suspendStatus;
    }

    public static String getRunningStatus() {
        return runningStatus;
    }

    public String getStartUserName() {
        return startUserName;
    }

    public void setStartUserName(String startUserName) {
        this.startUserName = startUserName;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }

    public List<HistoricTaskInstanceResponse> getHistoricTaskList() {
        return historicTaskList;
    }

    public void setHistoricTaskList(List<HistoricTaskInstanceResponse> historicTaskList) {
        this.historicTaskList = historicTaskList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessInstanceStatus() {
        return processInstanceStatus;
    }

    public void setProcessInstanceStatus(String processInstanceStatus) {
        this.processInstanceStatus = processInstanceStatus;
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public String getCurrentTaskName() {
        return currentTaskName;
    }

    public void setCurrentTaskName(String currentTaskName) {
        this.currentTaskName = currentTaskName;
    }

    public String getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(String currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public String getCurrentAssign() {
        return currentAssign;
    }

    public void setCurrentAssign(String currentAssign) {
        this.currentAssign = currentAssign;
    }
}
