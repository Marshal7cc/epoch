package com.marshal.epoch.mybatis.exception;

import com.marshal.epoch.core.exception.CommonException;

/**
 * excel导入、导出异常
 *
 * @author Marshal Yuan
 * @date 2020/5/30
 */
public class ExcelException extends CommonException {

    public ExcelException(String message) {
        super(message);
    }
}
