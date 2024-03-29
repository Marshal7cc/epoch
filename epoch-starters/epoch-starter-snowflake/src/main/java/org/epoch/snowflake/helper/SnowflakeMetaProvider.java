package org.epoch.snowflake.helper;

import org.springframework.context.ApplicationContext;

/**
 * @author Marshal
 * @date 2021/3/4
 */
public interface SnowflakeMetaProvider {
    int BIT_DATA_CENTER = 5;
    int BIT_WORKER = 5;
    long MAX_DATA_CENTER = ~(-1L << BIT_DATA_CENTER);
    long MAX_WORKER = ~(-1L << BIT_WORKER);

    /**
     * @param context 应用程序上下文
     * @return 数据中心机器ID
     */
    long dataCenterId(ApplicationContext context);

    /**
     * @param context 应用程序上下文
     * @return 工作机器ID
     */
    long workerId(ApplicationContext context);
}
