package com.marshal.epoch.message.enums;

/**
 * @auth: Marshal
 * @date: 2020/4/10
 * @desc:
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
