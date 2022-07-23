package org.epoch.core.constant;

/**
 * 返回信息
 *
 * @author Marshal
 */
public interface MessageResp {

    /**
     * 基础成功编码
     */
    interface Success {
        String BASIC = "success.success";
    }

    /**
     * 基础异常编码
     */
    interface Error {
        /**
         * 数据校验不通过
         */
        String DATA_INVALID = "error.data_invalid";
        /**
         * 资源不存在
         */
        String NOT_FOUND = "error.not_found";
        /**
         * 程序出现错误，请联系管理员
         */
        String ERROR = "error.error";
        /**
         * 网络异常，请稍后重试
         */
        String ERROR_NET = "error.network";
        /**
         * 记录不存在或版本不一致
         */
        String OPTIMISTIC_LOCK = "error.optimistic_lock";
        /**
         * 数据已存在，请不要重复提交
         */
        String DATA_EXISTS = "error.data_exists";
        /**
         * 数据不存在
         */
        String DATA_NOT_EXISTS = "error.data_not_exists";
        /**
         * 资源禁止访问
         */
        String FORBIDDEN = "error.forbidden";
        /**
         * 数据库异常：编码重复
         */
        String ERROR_CODE_REPEAT = "error.code_repeat";
        /**
         * 数据库异常：编号重复
         */
        String ERROR_NUMBER_REPEAT = "error.number_repeat";
        /**
         * SQL执行异常
         */
        String ERROR_SQL_EXCEPTION = "error.sql_exception";
        /**
         * 请登录后再进行操作！
         */
        String NOT_LOGIN = "error.not_login";
        /**
         * 不能为空
         */
        String NOT_NULL = "error.not_null";
        /**
         * 响应超时
         */
        String TIMEOUT = "error.timeout";
        /**
         * 服务器繁忙，请稍后重试
         */
        String SERVER_BUSY = "error.serverBusy";
    }
}
