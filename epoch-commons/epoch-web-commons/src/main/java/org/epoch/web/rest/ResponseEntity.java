package org.epoch.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * 标准返回结果
 *
 * @author Marshal
 * @date 2019/8/27
 */
@Builder
public class ResponseEntity<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success = true;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public ResponseEntity() {
    }

    public ResponseEntity(String message) {
        this.message = message;
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseEntity(String code, Boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public ResponseEntity(Boolean success, T data, String code, String message) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
