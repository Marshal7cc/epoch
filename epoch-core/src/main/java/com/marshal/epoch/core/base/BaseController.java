package com.marshal.epoch.core.base;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 基础controller
 */
public class BaseController {

    /**
     * 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return ResponseUtil.responseErr(e.getMessage());
    }
}
