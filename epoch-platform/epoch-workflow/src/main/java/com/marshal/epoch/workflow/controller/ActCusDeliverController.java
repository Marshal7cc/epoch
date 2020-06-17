package com.marshal.epoch.workflow.controller;


import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.workflow.entity.ActCusDeliver;
import com.marshal.epoch.workflow.service.ActCusDeliverService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actcusdeliver")
public class ActCusDeliverController extends BaseController<ActCusDeliver, ActCusDeliverService> {


}
