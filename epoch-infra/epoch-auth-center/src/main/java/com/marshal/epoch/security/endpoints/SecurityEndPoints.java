package com.marshal.epoch.security.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Marshal
 * @date 2020/1/17
 */
@RestController
public class SecurityEndPoints {

    /**
     * oauth2 resource endpoint
     * 所有资源服务器请求接口前均会访问请求该接口
     *
     * @param principal
     * @return
     */
    @GetMapping("/user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

}
