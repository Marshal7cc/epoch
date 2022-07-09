package org.epoch.core.async;

import java.util.Date;

import lombok.Data;

/**
 * 异步任务
 *
 * @author Marshal
 * @date 2021/1/24
 */
@Data
public abstract class AsyncTask<T> {
    private Long taskId;
    private String taskCode;
    private String taskName;
    private String hostName;
    private String serviceName;
    private Long tenantId;
    private Long userId;
    private AsyncTaskState state;
    private String errorInfo;
    private String downloadUrl;
    private Date endDateTime;

    public abstract T doExecute();
}
