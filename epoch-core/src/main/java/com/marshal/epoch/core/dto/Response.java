package com.marshal.epoch.core.dto;

/**
 * @auth: Marshal
 * @date: 2019/8/27
 * @desc: 标准返回结果
 */
public class Response {

    /**
     * 本次请求是否成功
     */
    private Boolean success;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String message;

    public Response(Boolean success, Object data, Integer code, String message) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
