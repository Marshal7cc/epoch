package org.epoch.workflow.controller;


import org.epoch.mybatis.common.CommonController;
import org.epoch.workflow.entity.ActCusDeliver;
import org.epoch.workflow.service.ActCusDeliverService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actcusdeliver")
public class ActCusDeliverController extends CommonController<ActCusDeliver, ActCusDeliverService> {


}
