package com.marshal.epoch.workflow.controller;


import javax.servlet.http.HttpServletRequest;

import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.core.rest.ResponseEntity;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auth: Marshal
 * @date: 2019/4/6
 * @desc:
 */
@RestController
@RequestMapping("/workflow")
public class RuntimeController {

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping("/runtime/prc/suspend/{procId}")
    public ResponseEntity suspendProc(HttpServletRequest request, @PathVariable String procId) {
        runtimeService.suspendProcessInstanceById(procId);
        return Response.success("挂起成功!");
    }

    @RequestMapping("/runtime/prc/active/{procId}")
    public ResponseEntity activeProc(HttpServletRequest request, @PathVariable String procId) {
        runtimeService.activateProcessInstanceById(procId);
        return Response.success("激活成功!");
    }

    @GetMapping(value = "/runtime/prc/end/{procId}")
    @ResponseBody
    public ResponseEntity endProc(HttpServletRequest request, @PathVariable String procId) {
        runtimeService.deleteProcessInstance(procId, "adminStop");
        return Response.success("终止成功!");
    }

    @RequestMapping(value = "/runtime/prc/back/{procId}", method = RequestMethod.POST)
    @ResponseBody
    public void backProc(HttpServletRequest request, @PathVariable String procId) {
//        IRequest iRequest = createRequestContext(request);
//        if (activitiService.isStartRecall(procId, iRequest.getEmployeeCode())) {
//            activitiService.startRecall(iRequest, procId, iRequest.getEmployeeCode());
//        } else if (activitiService.isTaskRecall(procId, iRequest.getEmployeeCode())) {
//            activitiService.taskRecall(iRequest, procId, iRequest.getEmployeeCode());
//        }
    }
}
