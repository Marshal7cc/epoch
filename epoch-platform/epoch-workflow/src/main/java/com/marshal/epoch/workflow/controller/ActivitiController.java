package com.marshal.epoch.workflow.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.util.RequestHelper;
import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.workflow.entity.HistoricProcessInstanceResponseExt;
import com.marshal.epoch.workflow.entity.TaskActionRequestExt;
import com.marshal.epoch.workflow.entity.TaskResponseExt;
import com.marshal.epoch.workflow.exception.TaskHandleException;
import com.marshal.epoch.workflow.service.ActivitiService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auth: Marshal
 * @date: 2018/10/29
 * @desc: activiti工作流controller
 */
@RestController
@RequestMapping("/workflow")
public class ActivitiController {

//    @Autowired
//    protected ProcessEngineConfiguration processEngineConfiguration;
//
//    @Autowired
//    TaskService taskService;
//
//    @Autowired
//    HistoryService historyService;
//
//    @Autowired
//    ActivitiService activitiService;
////
////    @Autowired
////    HrEmployeeService employeeService;
//
//    /**
//     * 任务处理
//     *
//     * @param request
//     * @param taskId
//     * @param taskActionRequest
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/task/handle/{taskId}")
//    @ResponseBody
//    public ResponseEntity taskHandle(HttpServletRequest request,
//                                     @PathVariable String taskId,
//                                     @RequestBody TaskActionRequestExt taskActionRequest) throws Exception {
////        SessionContext sessionContext = RequestHelper.getSessionContext(request);
//        activitiService.handleTask(sessionContext, taskId, taskActionRequest, false);
//        return Response.success();
//    }
//
//    /**
//     * 任务处理(管理员)
//     *
//     * @param request
//     * @param taskId
//     * @param taskActionRequest
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/task/handle/admin/{taskId}")
//    @ResponseBody
//    public ResponseEntity taskHandleAdmin(HttpServletRequest request,
//                                        @PathVariable String taskId,
//                                        @RequestBody TaskActionRequestExt taskActionRequest) throws TaskHandleException {
//        SessionContext sessionContext = RequestHelper.getSessionContext(request);
//        activitiService.handleTask(sessionContext, taskId, taskActionRequest, true);
//        return Response.success();
//    }
//
//    /**
//     * 我的待办列表
//     *
//     * @return
//     */
//    @RequestMapping("/tasks/query")
//    public ResponseEntity queryTasks(HttpServletRequest request,
//                                   @RequestParam int start,
//                                   @RequestParam int size) {
//        SessionContext sessionContext = RequestHelper.getSessionContext(request);
//        String employeeCode = sessionContext.getEmployeeCode();
//
//        int total = taskService.createTaskQuery().taskAssignee(employeeCode).list().size();
//
//        List<Task> tasks = taskService.createTaskQuery().taskAssignee(employeeCode).listPage(start, size);
//
//        List<TaskResponseExt> taskList = tasks.stream().map(task -> new TaskResponseExt(task)).collect(Collectors.toList());
//
//        return new ResponseEntity(activitiService.getTaskList(taskList), total);
//    }
//
//    /**
//     * 我的待办，任务详情
//     *
//     * @param request
//     * @param taskId
//     * @return
//     */
//    @RequestMapping("/tasks/{taskId}/details")
//    @ResponseBody
//    public TaskResponseExt taskDetails(HttpServletRequest request,
//                                       @PathVariable String taskId) {
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        TaskResponseExt taskExt = new TaskResponseExt(task);
//
//        return activitiService.getTaskDetail(taskExt);
//    }
//
//    /**
//     * 流程监控查询
//     *
//     * @param request
//     * @param start
//     * @param size
//     * @param requestParam
//     * @return
//     */
//    @RequestMapping(value = "/process-instances/monitor/query")
//    public ResponseEntity queryAllProcessInstances(HttpServletRequest request,
//                                                 @RequestParam int start,
//                                                 @RequestParam int size,
//                                                 @RequestBody Map<String, String> requestParam) {
//        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
//
//        if ("Y".equals(requestParam.get("involved"))) {
//            historicProcessInstanceQuery.involvedUser(sessionContext.getEmployeeCode());
//        }
//        if ("Y".equals(requestParam.get("started"))) {
//            historicProcessInstanceQuery.startedBy(sessionContext.getEmployeeCode());
//        }
//
//        Integer total = historicProcessInstanceQuery.list().size();
//
//        List<HistoricProcessInstance> processInstances = historicProcessInstanceQuery.listPage(start, size);
//
//        List<HistoricProcessInstanceResponseExt> historicProcessInstances = processInstances.stream().map(processInstance -> new HistoricProcessInstanceResponseExt(processInstance)).collect(Collectors.toList());
//
//        return new ResponseEntity(activitiService.getHistoricProcessInstanceList(historicProcessInstances), total);
//    }
//
//    /**
//     * 流程监控-流程详情
//     *
//     * @param request
//     * @param processInstanceId
//     * @return
//     */
//    @GetMapping("/processInstance/{processInstanceId}")
//    @ResponseBody
//    public HistoricProcessInstanceResponseExt instanceDetail(HttpServletRequest request,
//                                                             @PathVariable String processInstanceId) {
//        SessionContext sessionContext = RequestHelper.getSessionContext(request);
//        HistoricProcessInstanceResponseExt processInstanceDetail = activitiService.getProcessInstanceDetail(sessionContext, processInstanceId);
//        return processInstanceDetail;
//    }

}
