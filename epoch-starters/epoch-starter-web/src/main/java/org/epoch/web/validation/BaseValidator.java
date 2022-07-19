package org.epoch.web.validation;

import java.util.Collection;
import javax.validation.Validator;

import org.epoch.core.util.ValidUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseValidateService
 *
 * @author Marshal
 */
public class BaseValidator {
    @Autowired
    private Validator validator;

    /**
     * 验证单个对象
     *
     * @param object 验证对象
     * @param groups 验证分组
     * @param <T>    Bean 泛型
     */
    protected <T> void validObject(T object, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, object, groups);
    }

    /**
     * 验证单个对象
     *
     * @param object  验证对象
     * @param groups  验证分组
     * @param process 异常信息处理
     * @param <T>     Bean 泛型
     */
    protected <T> void validObject(T object, ValidUtils.ValidationResult process, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, object, process, groups);
    }

    /**
     * 迭代验证集合元素，使用默认异常信息处理
     *
     * @param collection Bean集合
     * @param groups     验证组
     * @param <T>        Bean 泛型
     */
    protected <T> void validList(Collection<T> collection, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, collection, groups);
    }

    /**
     * 迭代验证集合元素，使用默认异常信息处理
     *
     * @param collection Bean集合
     * @param groups     验证组
     * @param process    异常信息处理
     * @param <T>        Bean 泛型
     */
    protected <T> void validList(Collection<T> collection, ValidUtils.ValidationResult process, @SuppressWarnings("rawtypes") Class... groups) {
        ValidUtils.valid(validator, collection, process, groups);
    }
}
