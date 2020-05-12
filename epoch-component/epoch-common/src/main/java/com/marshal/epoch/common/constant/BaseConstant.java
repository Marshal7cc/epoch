package com.marshal.epoch.common.constant;

/**
 * @auth: Marshal
 * @date: 2019/8/27
 * @desc: 基础常量
 */
public interface BaseConstant {

    /**
     * 基本常量 - 是、否 标记.
     */
    String YES = "Y";
    String NO = "N";

    /**
     * 时间格式
     */
    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 返回报文头 格式,编码
     */
    String APPLICATION_JSON_UTF8 = "application/json;charset=utf-8";
    String TEXT_HTML_UTF8 = "text/html;charset=utf-8";

    /**
     * 请求返回message
     */
    String OPERATE_SUCCESS = "操作成功!";
    String OPERATE_FAIL = "操作失败!";
    String EXCEL_IMPORT_FAIL = "excel导入失败!";
    String EXCEL_EXPORT_FAIL = "excel导出失败!";

    /**
     * request method
     */
    String POST = "POST";
    String GET = "GET";
    String PUT = "PUT";
    String DELETE = "DELETE";

    /**
     * 文件后缀
     */
    String FILE_SUFFIX_PNG = "png";
    String FILE_SUFFIX_GIF = "gif";

}
