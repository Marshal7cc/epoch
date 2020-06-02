package com.marshal.epoch.message.enums;

/**
 * 消息中间件类型枚举
 *
 * @author Marshal
 * @date 2020/4/10
 */
public enum MessageTypeEnum {
    /**
     * RabbitMQ
     */
    RABBIT("rabbit"),
    /**
     * redis
     */
    REDIS("redis");

    private String type;

    MessageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
