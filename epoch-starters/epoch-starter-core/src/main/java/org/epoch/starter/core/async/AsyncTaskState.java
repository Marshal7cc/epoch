package org.epoch.starter.core.async;

/**
 * 异步任务状态
 *
 * @author Marshal
 * @date 2021/1/24
 */
public enum AsyncTaskState {
    /**
     * 正在进行
     */
    DOING("doing"),
    /**
     * 已结束
     */
    DONE("done"),
    /**
     * 已取消
     */
    CANCELLED("cancelled");

    private String code;

    AsyncTaskState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
