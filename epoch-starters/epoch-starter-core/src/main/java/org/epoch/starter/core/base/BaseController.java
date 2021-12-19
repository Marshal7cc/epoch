package org.epoch.starter.core.base;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Marshal
 * @date 2021/1/24
 */
public class BaseController extends BaseValidator {
    /**
     * function:去除QueryString字符串前后空格
     * [post/put请求对象里面的String字段无法操作]
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }
}
