package com.marshal.epoch.rulengine.test.controller;

import java.util.Arrays;

import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.rulengine.service.RuleEngineService;
import com.marshal.epoch.rulengine.test.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>name:TestController</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/9
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RuleEngineService ruleEngineService;

    @GetMapping
    public ResponseEntity test() {
        Order order = new Order();
        order.setOriginalPrice(120D);
        ruleEngineService.match(Arrays.asList(order));

        return Response.success();
    }
}
