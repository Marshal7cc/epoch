package org.epoch.workflow.entity;

import org.activiti.rest.service.api.runtime.task.TaskActionRequest;

/**
 * @auth: Marshal
 * @date: 2019/4/6
 * @desc: 任务处理请求信息
 */
public class TaskActionRequestExt extends TaskActionRequest {

    public static final String ACTION_REJECT = "reject";

    private String approveResult;

    private String comment;

    private String jumpTarget;

    private String jumpTargetName;

    private String CarbonCopyUsers;

    private String currentTaskId;

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJumpTarget() {
        return jumpTarget;
    }

    public void setJumpTarget(String jumpTarget) {
        this.jumpTarget = jumpTarget;
    }

    public String getCarbonCopyUsers() {
        return CarbonCopyUsers;
    }

    public void setCarbonCopyUsers(String carbonCopyUsers) {
        CarbonCopyUsers = carbonCopyUsers;
    }

    public String getJumpTargetName() {
        return jumpTargetName;
    }

    public void setJumpTargetName(String jumpTargetName) {
        this.jumpTargetName = jumpTargetName;
    }

    public String getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(String currentTaskId) {
        this.currentTaskId = currentTaskId;
    }
}
