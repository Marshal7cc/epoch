package com.marshal.epoch.workflow.entity;

import java.util.Date;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.rest.service.api.history.HistoricTaskInstanceResponse;

/**
 * @auth: Marshal
 * @date: 2019/2/11
 * @desc: 历史审批
 */
public class HistoricTaskInstanceResponseExt extends HistoricTaskInstanceResponse {

    protected Date startTime;
    protected Date endTime;
    protected Date claimTime;
    protected Date dueDate;
    private String comment;
    private String assigneeName;
    private String completeBy;
    private String action;
    private String positionName;
    private String unitName;

    public HistoricTaskInstanceResponseExt() {
    }

    public HistoricTaskInstanceResponseExt(HistoricTaskInstance taskInstance) {
        setAssignee(taskInstance.getAssignee());
        setClaimTime(taskInstance.getClaimTime());
        setDeleteReason(taskInstance.getDeleteReason());
        setDescription(taskInstance.getDescription());
        setDueDate(taskInstance.getDueDate());
        setDurationInMillis(taskInstance.getDurationInMillis());
        setEndTime(taskInstance.getEndTime());
        setExecutionId(taskInstance.getExecutionId());
        setFormKey(taskInstance.getFormKey());
        setId(taskInstance.getId());
        setName(taskInstance.getName());
        setOwner(taskInstance.getOwner());
        setParentTaskId(taskInstance.getParentTaskId());
        setPriority(taskInstance.getPriority());
        setProcessDefinitionId(taskInstance.getProcessDefinitionId());
        setTenantId(taskInstance.getTenantId());
        setCategory(taskInstance.getCategory());
    }

    public HistoricTaskInstanceResponseExt(HistoricActivityInstance historicActivityInstance) {
        setAssignee(historicActivityInstance.getAssignee());
        setEndTime(historicActivityInstance.getEndTime());
        setStartTime(historicActivityInstance.getStartTime());
        setExecutionId(historicActivityInstance.getExecutionId());
        setId(historicActivityInstance.getId());
        setName(historicActivityInstance.getActivityName());
        setProcessDefinitionId(historicActivityInstance.getProcessDefinitionId());
        setTenantId(historicActivityInstance.getTenantId());
        setAssigneeName(historicActivityInstance.getAssignee());
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(String completeBy) {
        this.completeBy = completeBy;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
