package com.marshal.epoch.auth.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @auth: Marshal
 * @date: 2020/1/17
 * @desc:
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
