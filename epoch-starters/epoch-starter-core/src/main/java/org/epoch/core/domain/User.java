package org.epoch.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 认证用户信息
 *
 * @author Marshal
 * @date 2020/1/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String username;

    public static final String FILED_USER_ID = "userId";
    public static final String FILED_USER_NAME = "userName";
}
