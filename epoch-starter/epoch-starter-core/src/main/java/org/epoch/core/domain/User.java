package org.epoch.core.domain;

import lombok.Data;

/**
 * 认证用户信息
 *
 * @author Marshal
 * @date 2020/1/27
 */
@Data
public class User {
    private Long userId;
    private String username;
}
