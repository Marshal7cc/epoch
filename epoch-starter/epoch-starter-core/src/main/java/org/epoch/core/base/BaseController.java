package org.epoch.core.base;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Marshal
 * @date 2021/1/24
 */
public class BaseController extends BaseValidator {
    /**
     * 控制层请求String字符串去除前后空格 主要用于get搜索接口请求参数 post/put请求对象里面的String字段无法操作
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }
}
