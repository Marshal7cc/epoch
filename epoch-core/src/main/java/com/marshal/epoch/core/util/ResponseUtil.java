package com.marshal.epoch.core.util;

import com.marshal.epoch.core.constant.BaseConstant;
import com.marshal.epoch.core.dto.PageableData;
import com.marshal.epoch.core.dto.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/8/27
 * @desc: response工具类
 */
public class ResponseUtil implements BaseConstant {

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    private ResponseUtil() {

    }

    /**
     * 请求成功
     *
     * @return
     */
    public static ResponseEntity responseOk() {
        return responseOk(OPERATE_SUCCESS);
    }

    public static ResponseEntity responseOk(String message) {
        return new ResponseEntity(message);
    }

    public static ResponseEntity responseOk(List<?> rows) {
        return new ResponseEntity(new PageableData(rows));
    }

    public static ResponseEntity responseOk(Object data) {
        return new ResponseEntity(data);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static ResponseEntity responseErr() {
        return responseErr(OPERATE_FAIL);
    }

    public static ResponseEntity responseErr(String message) {
        return new ResponseEntity(false, message);
    }

}
