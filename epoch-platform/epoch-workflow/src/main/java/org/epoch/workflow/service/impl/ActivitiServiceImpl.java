package org.epoch.workflow.service.impl;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.epoch.workflow.entity.*;
import org.epoch.workflow.exception.TaskHandleException;

import org.epoch.workflow.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.CommentEntityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.rest.service.api.RestResponseFactory;
import org.apache.commons.lang3.StringUtils;
import org.epoch.workflow.entity.HistoricProcessInstanceResponseExt;
import org.epoch.workflow.entity.HistoricTaskInstanceResponseExt;
import org.epoch.workflow.entity.TaskActionRequestExt;
import org.epoch.workflow.entity.TaskResponseExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auth: Marshal
 * @date: 2019/2/6
 * @desc: Activiti工作流service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RestResponseFactory restResponseFactory;

    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private HrEmployeeApi hrEmployeeApi;

    @Override
    public void handleTask(String taskId, TaskActionRequestExt taskActionRequest, boolean isAdmin) throws TaskHandleException, IllegalArgumentException {
//        if (StringUtils.isEmpty(taskActionRequest.getAction())) {
//            throw new TaskHandleException(TaskHandleException.UNKNOWN_OPERATOR);
//        }
//
//        if (taskId.split(",").length > 1) {
//            throw new IllegalArgumentException("多审批人节点暂不支持此操作!");
//        }
//
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        if (task == null) {
//            throw new ActivitiObjectNotFoundException("Could not find a task with id '" + taskId + "'.", Task.class);
//        }
//
//        if (!(isAdmin || task.getAssignee().equals(sessionContext.getEmployeeCode()))) {
//            throw new TaskHandleException(TaskHandleException.COMPLETE_TASK_NEED_ASSIGNEE_OR_ADMIN);
//        }
//
//        Authentication.setAuthenticatedUserId(sessionContext.getEmployeeCode());
//
//        try {
//            // 处理抄送
////            carbonCopy(sessionContext, task, taskActionRequest);
//
//            TaskHandler taskHandler = TaskHandlerProvider.getTaskHandler(taskActionRequest.getAction());
//            taskHandler.handle(sessionContext, task, taskActionRequest);
//
//        } catch (Exception e) {
////            self().saveException(taskId, e);
//            throw e;
//        }
    }


    /**
     * 我的待办-task 列表
     *
     * @param taskList
     * @return
     */
    @Override
    public List<TaskResponseExt> getTaskList(List<TaskResponseExt> taskList) {
        taskList.forEach(item -> {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(item.getProcessInstanceId()).singleResult();
            //流程名称
            if (StringUtils.isNotEmpty(processInstance.getProcessDefinitionName())) {
                item.setProcessName(processInstance.getProcessDefinitionName());
            }
            //流程发起人
//            if (StringUtils.isNotEmpty(processInstance.getStartUserId())) {
//                item.setStartUserId(processInstance.getStartUserId());
//                item.setStartUserName(employeeMapper.getEmployeeNameByCode(processInstance.getStartUserId()));
//            }
//            //当前审批人
//            if (StringUtils.isNotEmpty(item.getAssignee())) {
//                item.setAssignee(employeeMapper.getEmployeeNameByCode(item.getAssignee()));
//            }
        });
        return taskList;
    }

    @Override
    public TaskResponseExt getTaskDetail(TaskResponseExt task) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        //流程实例
        task.setProcessInstance(restResponseFactory.createProcessInstanceResponse(processInstance));
        //流程名称
        if (StringUtils.isNotEmpty(processInstance.getProcessDefinitionName())) {
            task.setProcessName(processInstance.getProcessDefinitionName());
        }
        //流程发起人
        if (StringUtils.isNotEmpty(processInstance.getStartUserId())) {
            task.setStartUserId(processInstance.getStartUserId());
//            task.setStartUserName(employeeMapper.getEmployeeNameByCode(processInstance.getStartUserId()));
        }
        //审批历史
        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
        List<HistoricTaskInstanceResponseExt> list = new ArrayList<>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            setHistoricActivityInstanceResponseExt(historicActivityInstance, list, task.getProcessInstanceId());
        }
        list.sort(Comparator.comparing(HistoricTaskInstanceResponseExt::getEndTime));
        task.getHistoricTaskList().addAll(list);

        return task;
    }

    private void setHistoricActivityInstanceResponseExt(HistoricActivityInstance historicActivityInstance,
                                                        List<HistoricTaskInstanceResponseExt> list,
                                                        String processInstanceId) {
        String activityType = historicActivityInstance.getActivityType();
        if ("userTask".equals(activityType)) {
            List<Comment> comments = getCommentOfType(historicActivityInstance.getTaskId(), "comment");
            List<Comment> actions = getCommentOfType(historicActivityInstance.getTaskId(), "action");
            if (comments != null && comments.size() != 0) {
                for (int index = comments.size() - 1; index >= 0; index--) {
                    HistoricTaskInstanceResponseExt historicTaskInstanceResponseExt = new HistoricTaskInstanceResponseExt(
                            historicActivityInstance);
                    CommentEntityImpl commentEntity = (CommentEntityImpl) comments.get(index);
                    historicTaskInstanceResponseExt.setComment(commentEntity.getMessage());
                    historicTaskInstanceResponseExt.setAction(actions.get(index).getFullMessage());
                    historicTaskInstanceResponseExt.setAssignee(actions.get(index).getUserId());
                    StringBuilder sb = new StringBuilder();
//                    String temp = employeeMapper.getEmployeeNameByCode(actions.get(index).getUserId());
//                    sb.append(StringUtils.isEmpty(temp) ? "" : temp);
                    sb.append(StringUtils.isEmpty(actions.get(index).getUserId()) ? "" : "(" + actions.get(index).getUserId() + ")");
                    historicTaskInstanceResponseExt.setAssigneeName(sb.toString());
                    historicTaskInstanceResponseExt.setEndTime(actions.get(index).getTime());
                    list.add(historicTaskInstanceResponseExt);
                }
            }
            return;
        }
        HistoricTaskInstanceResponseExt historicTaskInstanceResponseExt = new HistoricTaskInstanceResponseExt(
                historicActivityInstance);
        if ("startEvent".equalsIgnoreCase(activityType)
                && StringUtils.isEmpty(historicActivityInstance.getActivityName())) {
            String startUser = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
                    .list().get(0).getStartUserId();
            historicTaskInstanceResponseExt.setAssignee(startUser);
//            historicTaskInstanceResponseExt.setAssigneeName(employeeMapper.getEmployeeNameByCode(startUser) + "(" + startUser + ")");
            String start = "开始";
            historicTaskInstanceResponseExt.setName(start);
        }
        if ("endEvent".equalsIgnoreCase(activityType)
                && StringUtils.isEmpty(historicActivityInstance.getActivityName())) {
            String end = "结束";
            historicTaskInstanceResponseExt.setName(end);
        }
        if (!"exclusiveGateway".equals(activityType) && !"parallelGateway".equals(activityType)
                && !"eventBasedGateway".equals(activityType) && !"inclusiveGateway".equals(activityType)
                && null == historicActivityInstance.getDeleteReason()) {
            list.add(historicTaskInstanceResponseExt);
        }
    }

    protected List<Comment> getCommentOfType(String taskId, String type) {
        List<Comment> list = taskService.getTaskComments(taskId, type);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<HistoricProcessInstanceResponseExt> getHistoricProcessInstanceList(List<HistoricProcessInstanceResponseExt> historicProcessInstanceList) {
        for (HistoricProcessInstanceResponseExt historicProcessInstanceExt : historicProcessInstanceList) {
            //申请人
//            historicProcessInstanceExt.setStartUserName(employeeMapper.getEmployeeNameByCode(historicProcessInstanceExt.getStartUserId()));
            //流程状态
            if (historicProcessInstanceExt.getEndTime() != null) {
                historicProcessInstanceExt.setProcessInstanceStatus(HistoricProcessInstanceResponseExt.endStatus);
            } else {
                historicProcessInstanceExt.setProcessInstanceStatus(HistoricProcessInstanceResponseExt.runningStatus);
            }
            List<Execution> list1 = runtimeService.createExecutionQuery().processInstanceId(historicProcessInstanceExt.getId()).list();
            for (Execution ls : list1) {
                if (ls.isSuspended()) {
                    historicProcessInstanceExt.setProcessInstanceStatus(HistoricProcessInstanceResponseExt.suspendStatus);
                    break;
                }
            }
            //当前节点和审批人(流程状态为非结束时)
            if (HistoricProcessInstanceResponseExt.runningStatus.equals(historicProcessInstanceExt.getProcessInstanceStatus())) {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(historicProcessInstanceExt.getId()).list();
                if (!tasks.isEmpty()) {
                    String[] currentAssignees = new String[tasks.size()];
                    Set taskName = new HashSet();
                    Set taskId = new HashSet();
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        taskName.add(task.getName());
                        taskId.add(task.getId());
//                        currentAssignees[i] = employeeMapper.getEmployeeNameByCode(task.getAssignee());
                    }
                    //设置当前节点
                    historicProcessInstanceExt.setCurrentTaskId(org.springframework.util.StringUtils.collectionToCommaDelimitedString(taskId));
                    historicProcessInstanceExt.setCurrentTaskName(org.springframework.util.StringUtils.collectionToCommaDelimitedString(taskName));
                    // 设置当前审批人
                    historicProcessInstanceExt.setCurrentAssign(StringUtils.join(currentAssignees, ","));
                }

            }
        }
        return historicProcessInstanceList;
    }

    @Override
    public HistoricProcessInstanceResponseExt getProcessInstanceDetail(String processInstanceId) {
        // 查询流程实例历史
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).list().iterator().next();

        HistoricProcessInstanceResponseExt historicProcessInstanceResponseExt = new HistoricProcessInstanceResponseExt(historicProcessInstance);

        // 设置申请人，流程名称
//        historicProcessInstanceResponseExt.setStartUserName(employeeMapper.getEmployeeNameByCode(historicProcessInstanceResponseExt.getStartUserId()));

        // 获取流程活动历史
        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        List<HistoricTaskInstanceResponseExt> list = new ArrayList<>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            setHistoricActivityInstanceResponseExt(historicActivityInstance, list, processInstanceId);
        }
        list.sort(Comparator.comparing(HistoricTaskInstanceResponseExt::getEndTime));
        historicProcessInstanceResponseExt.getHistoricTaskList().addAll(list);

        // 设置最后一个任务节点的formKey
        String businessKey = historicProcessInstance.getBusinessKey();

        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByTaskCreateTime().desc().list();

        String formKey = null;
        if (historicTaskInstances.size() > 0) {
            HistoricTaskInstance historicTaskInstance = historicTaskInstances.get(0);
            formKey = historicTaskInstance.getFormKey();
        }
        historicProcessInstanceResponseExt.setFormUrl(formKey + "?businessKey=" + businessKey);

        return historicProcessInstanceResponseExt;
    }
}
