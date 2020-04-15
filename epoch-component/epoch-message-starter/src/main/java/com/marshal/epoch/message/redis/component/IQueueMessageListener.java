/*
 * #{copyright}#
 */

package com.marshal.epoch.message.redis.component;


/**
 * @param
 */
public interface IQueueMessageListener {

    String DEFAULT_METHOD_NAME = "onQueueMessage";

    /**
     * @return 队列名称
     */
    String getQueue();

    /**
     * @param message 经过反序列化的数据
     * @param queue   queue name
     */
    void onQueueMessage(Object message, String queue);
}
