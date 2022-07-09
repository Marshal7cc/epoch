package org.epoch.security.constants;

/**
 * <p>name:SecurityConstants</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal
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

    interface ResponseMessage {
        String INVALID_REFRESH_TOKEN = "Invalid refresh token";
        String LOCKER = "locked";
    }
}
