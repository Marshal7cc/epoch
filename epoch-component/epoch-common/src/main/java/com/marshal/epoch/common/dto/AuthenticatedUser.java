package com.marshal.epoch.common.dto;

import lombok.Data;

/**
 * @auth: Marshal
 * @date: 2020/1/27
 * @desc: 用于RequestHelper, 获取当前认证用户信息
 */
@Data
public class AuthenticatedUser {

    private Long userId;

    private String username;

}
