package org.epoch.core.async;

import java.util.Map;

/**
 * 异步模版
 *
 * @author Marshal
 * @date 2021/1/24
 */
public interface AsyncTemplate<T extends AsyncTask> {

    default String submit(T dto, Executor executor) {
        String uuid = executor.execute();
        afterSubmit(dto);
        return uuid;
    }

    void afterSubmit(T dto);

    Object doWhenFinish(T dto, Map<String, Object> additionInfo);

    Object doWhenOccurException(T dto, Throwable e);
}
