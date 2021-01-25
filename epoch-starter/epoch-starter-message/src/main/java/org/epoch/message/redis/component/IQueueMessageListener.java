/*
 * #{copyright}#
 */

package org.epoch.message.redis.component;


/**
 * @author Marshal
 */
public interface IQueueMessageListener {

    String DEFAULT_METHOD_NAME = "onQueueMessage";

    /**
     * 获取队列名称
     *
     * @return 队列名称
     */
    String getQueue();

    /**
     * 收到消息
     *
     * @param message 经过反序列化的数据
     * @param queue queue name
     */
    void onQueueMessage(Object message, String queue);
}
