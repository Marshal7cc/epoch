package com.marshal.epoch.security.constants;

import java.util.Arrays;
import java.util.List;

/**
 * <p>name:SecurityConstants</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/16
 */
public interface SecurityConstants {
    /**
     * 资源服务器默认白名单
     */
    String[] RESOURCE_SERVER_DEFAULT_WHITE_LIST = {
            "/",
            "/csrf",
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-resources",
            "/webjars/springfox-swagger-ui/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security"
    };
}
