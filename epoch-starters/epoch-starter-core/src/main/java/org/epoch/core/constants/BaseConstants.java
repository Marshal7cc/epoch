package org.epoch.core.constants;

import java.util.Locale;

/**
 * 基础常量
 *
 * @author Marshal
 * @date 2019/8/27
 */
public interface BaseConstants {

    /**
     * 匿名用户ID
     */
    String ANONYMOUS_USER_ID = "0";

    /**
     * 匿名用户名
     */
    String ANONYMOUS_USER_NAME = "anonymousUser";

    /**
     * 默认页码
     */
    String PAGE = "0";
    /**
     * 默认页面大小
     */
    String SIZE = "10";

    /**
     * 默认页码字段名
     */
    String PAGE_FIELD_NAME = "page";
    /**
     * 默认页面大小字段名
     */
    String SIZE_FIELD_NAME = "size";


    /**
     * body
     */
    String FIELD_BODY = "body";
    /**
     * KEY content
     */
    String FIELD_CONTENT = "content";

    /**
     * 默认语言
     */
    Locale DEFAULT_LOCALE = Locale.CHINA;

    /**
     * 默认语言
     */
    String DEFAULT_LOCALE_STR = Locale.CHINA.toString();

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
     * 默认环境
     */
    String DEFAULT_ENV = "dev";


    /**
     * 1/0
     */
    interface Status {
        /**
         * 1
         */
        String ENABLE = "1";
        /**
         * 0
         */
        String DISABLE = "0";
    }

    interface HeaderParam {

        /**
         * header传输的参数统一前缀
         */
        String REQUEST_HEADER_PARAM_PREFIX = "param-";
        String BASIC = "basic";
    }


    /**
     * 文件后缀
     */
    interface FileSuffix {
        String PNG = "png";
        String GIF = "gif";
    }

}
