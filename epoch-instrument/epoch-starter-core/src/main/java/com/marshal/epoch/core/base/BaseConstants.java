package com.marshal.epoch.core.base;

/**
 * 基础常量
 *
 * @author Marshal
 * @date 2019/8/27
 */
public interface BaseConstants {

    /**
     * 基本常量 - 是、否 标记.
     */
    interface Flag {
        String YES = "Y";
        String NO = "N";
    }

    /**
     * 标点符号
     */
    interface Symbol {
        String SIGH = "!";
        String AT = "@";
        String WELL = "#";
        String DOLLAR = "$";
        String RMB = "￥";
        String SPACE = " ";
        String LB = System.getProperty("line.separator");
        String PERCENTAGE = "%";
        String AND = "&";
        String STAR = "*";
        String MIDDLE_LINE = "-";
        String LOWER_LINE = "_";
        String EQUAL = "=";
        String PLUS = "+";
        String COLON = ":";
        String SEMICOLON = ";";
        String COMMA = ",";
        String POINT = ".";
        String SLASH = "/";
        String VERTICAL_BAR = "|";
        String DOUBLE_SLASH = "//";
        String BACKSLASH = "\\";
        String QUESTION = "?";
        String LEFT_BIG_BRACE = "{";
        String RIGHT_BIG_BRACE = "}";
        String LEFT_MIDDLE_BRACE = "[";
        String RIGHT_MIDDLE_BRACE = "]";
        String BACKQUOTE = "`";
    }

    /**
     * 请求头参数
     */
    interface HeaderParam {
        String REQUEST_HEADER_PARAM_PREFIX = "param-";
        String BASIC = "basic";
    }

    /**
     * 状态码
     */
    interface ResponseCode {
        int SUCCESS = 200;
        int FAIL = 500;
    }

    /**
     * 返回信息
     */
    interface ResponseMessage {
        String SUCCESS = "success.operate_success";
        String FAIL = "error.operate_fail";
        String DATA_INVALID = "error.data_invalid";
        String NOT_FOUND = "error.not_found";
        String ERROR = "error.error";
        String ERROR_NET = "error.network";
        String OPTIMISTIC_LOCK = "error.optimistic_lock";
        String DATA_EXISTS = "error.data_exists";
        String DATA_NOT_EXISTS = "error.data_not_exists";
        String FORBIDDEN = "error.forbidden";
        String ERROR_CODE_REPEAT = "error.code_repeat";
        String ERROR_NUMBER_REPEAT = "error.number_repeat";
        String ERROR_SQL_EXCEPTION = "error.sql_exception";
        String NOT_LOGIN = "error.not_login";
        String NOT_NULL = "error.not_null";
    }

    /**
     * 数字
     */
    interface Digital {
        int NEGATIVE_ONE = -1;
        int ZERO = 0;
        int ONE = 1;
        int TWO = 2;
        int FOUR = 4;
        int EIGHT = 8;
        int SIXTEEN = 16;
    }

    /**
     * 常见表达式
     */
    interface Pattern {
        String DATE = "yyyy-MM-dd";
        String DATETIME = "yyyy-MM-dd HH:mm:ss";
        String DATETIME_MM = "yyyy-MM-dd HH:mm";
        String DATETIME_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
        String TIME = "HH:mm";
        String TIME_SS = "HH:mm:ss";
        String SYS_DATE = "yyyy/MM/dd";
        String SYS_DATETIME = "yyyy/MM/dd HH:mm:ss";
        String SYS_DATETIME_MM = "yyyy/MM/dd HH:mm";
        String SYS_DATETIME_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
        String NONE_DATE = "yyyyMMdd";
        String NONE_DATETIME = "yyyyMMddHHmmss";
        String NONE_DATETIME_MM = "yyyyMMddHHmm";
        String NONE_DATETIME_SSS = "yyyyMMddHHmmssSSS";
        String CST_DATETIME = "EEE MMM dd HH:mm:ss 'CST' yyyy";
        String NONE_DECIMAL = "0";
        String ONE_DECIMAL = "0.0";
        String TWO_DECIMAL = "0.00";
        String TB_NONE_DECIMAL = "#,##0";
        String TB_ONE_DECIMAL = "#,##0.0";
        String TB_TWO_DECIMAL = "#,##0.00";
    }

    /**
     * 文件后缀
     */
    interface FileSuffix {
        String PNG = "png";
        String GIF = "gif";
    }
}
