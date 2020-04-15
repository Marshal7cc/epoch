package com.marshal.epoch.message.enums;

/**
 * @auth: Marshal
 * @date: 2020/4/10
 * @desc: 消息中间件类型枚举
 */
public enum MessageTypeEnum {
    RABBIT("rabbit"),
    REDIS("redis");

    private String type;

    MessageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
