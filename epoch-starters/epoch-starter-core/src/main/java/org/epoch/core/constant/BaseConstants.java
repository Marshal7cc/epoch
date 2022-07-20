package org.epoch.core.constant;

import java.util.Locale;

/**
 * 基础常量
 *
 * @author Marshal
 * @date 2019/8/27
 */
public interface BaseConstants {
    /**
     * body
     */
    String FIELD_BODY = "body";
    /**
     * KEY content
     */
    String FIELD_CONTENT = "content";

    /**
     * KEY message
     */
    String FIELD_MSG = "message";
    /**
     * KEY failed
     */
    String FIELD_FAILED = "failed";
    /**
     * KEY success
     */
    String FIELD_SUCCESS = "success";
    /**
     * KEY errorMsg
     */
    String FIELD_ERROR_MSG = "errorMsg";
    /**
     * 默认编码
     */
    String DEFAULT_CHARSET = "UTF-8";

    /**
     * 默认国际管码
     */
    String DEFAULT_CROWN_CODE = "+86";

    /**
     * 默认时区
     */
    String DEFAULT_TIME_ZONE = "GMT+8";

    /**
     * 默认语言
     */
    Locale DEFAULT_LOCALE = Locale.CHINA;

    /**
     * 默认语言
     */
    String DEFAULT_LOCALE_STR = Locale.CHINA.toString();

    /**
     * 默认环境
     */
    String DEFAULT_ENV = "dev";

}
