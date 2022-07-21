package org.epoch.iam.api.controller.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.epoch.iam.domain.repository.SysUserRepository;
import org.epoch.web.rest.Response;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 * @date 2019/8/25
 */
@RestController
public class TestController {

    @Autowired
    private SysUserRepository sysUserService;

    @GetMapping("/user/info")
    public ResponseEntity user() {
        Map<String, Object> info = new HashMap<>(9);
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        info.put("roles", roles);
        info.put("introduction", "123");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "Marshal");
        return Response.success(info);
    }

    @GetMapping("/tx/test")
    public ResponseEntity txTest() {
        sysUserService.txTest();
        return Response.success();
    }
}
