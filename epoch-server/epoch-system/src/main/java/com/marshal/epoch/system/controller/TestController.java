package com.marshal.epoch.system.controller;

import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.util.ResponseUtil;
import com.marshal.epoch.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/8/25
 * @desc:
 */
@RestController
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user/info")
    public ResponseEntity user() {
        Map<String, Object> info = new HashMap<>();
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        info.put("roles", roles);
        info.put("introduction", "123");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "Marshal");
        return ResponseUtil.responseOk(info);
    }

    @GetMapping("/tx/test")
    public ResponseEntity txTest() {
        sysUserService.txTest();
        return ResponseUtil.responseOk();
    }
}
