package com.marshal.epoch.system.controller;

import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;
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

}
