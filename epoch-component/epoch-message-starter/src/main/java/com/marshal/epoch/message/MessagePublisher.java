package com.marshal.epoch.message;

/**
 * 消息生产者
 */
public interface MessagePublisher {

    /**
     * 根据消息发布请求发布消息
     *
     * @param request
     */
    void publish(MessagePublishRequest request);

}
