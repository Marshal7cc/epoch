package com.marshal.epoch.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth: Marshal
 * @date: 2019/8/25
 * @desc:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

}
