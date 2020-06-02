package com.marshal.epoch.core.domain;

import lombok.Data;

/**
 * 认证用户信息
 *
 * @author Marshal
 * @date 2020/1/27
 */
@Data
public class VerifiedUser {

    private Long userId;

    private String username;

}
