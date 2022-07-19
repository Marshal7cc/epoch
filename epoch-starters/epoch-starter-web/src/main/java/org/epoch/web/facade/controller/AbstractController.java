package org.epoch.web.facade.controller;

import org.epoch.web.validation.BaseValidator;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Marshal
 * @since 2022/7/10
 */
public class AbstractController extends BaseValidator {
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
