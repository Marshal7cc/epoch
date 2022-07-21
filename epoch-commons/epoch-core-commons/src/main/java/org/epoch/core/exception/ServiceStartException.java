package org.epoch.core.exception;

/**
 * 自定义服务启动异常
 *
 * @author Marshal
 * @date 2021/1/24
 */
public class ServiceStartException extends BaseException {

    private String failureCause;

    public ServiceStartException(String code, String failureCause, Object... parameters) {
        super(code, parameters);
        this.failureCause = failureCause;
    }

    public ServiceStartException(String code, Throwable cause, String failureCause, Object... parameters) {
        super(code, cause, parameters);
        this.failureCause = failureCause;
    }

    public ServiceStartException(String code, Throwable cause, String failureCause) {
        super(code, cause);
        this.failureCause = failureCause;
    }

    public ServiceStartException(Throwable cause, String failureCause, Object... parameters) {
        super(cause, parameters);
        this.failureCause = failureCause;
    }

    public String getFailureCause() {
        return failureCause;
    }
}
