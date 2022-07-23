package org.epoch.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.epoch.core.exception.BaseException;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 集合验证，JSR-303中不认为集合(eg. List&lt;T&gt;)是一个Bean，因此无法使用@Valid注解来验证内部元素
 * 如果需要使用@Valid注解验证List，需要提供一个Bean包含List对象，验证Bean来完成List校验，但这会改变json结构
 * </p>
 *
 * @author Marshal
 */
public class ValidUtils {
    private static final ValidationResult DEFAULT_PROCESS = new ValidationResult() {
    };

    private ValidUtils() {
    }

    /**
     * 验证单个元素，使用默认异常信息处理
     *
     * @param validator 验证器
     * @param object    Bean
     * @param groups    验证组
     * @param <T>       Bean 泛型
     */
    public static <T> void valid(Validator validator, T object, Class<?>... groups) {
        valid(validator, object, DEFAULT_PROCESS, groups);
    }

    /**
     * 验证单个元素，使用默认异常信息处理
     *
     * @param validator 验证器
     * @param object    Bean
     * @param process   异常信息处理
     * @param groups    验证组
     * @param <T>       Bean 泛型
     */
    public static <T> void valid(Validator validator, T object, ValidationResult process, Class<?>... groups) {
        Set<ConstraintViolation<T>> violationSet;
        if (groups == null) {
            violationSet = validator.validate(object);
        } else {
            violationSet = validator.validate(object, groups);
        }
        if (process != null) {
            process.process(violationSet);
        }
    }

    /**
     * 迭代验证集合元素，使用默认异常信息处理
     *
     * @param validator  验证器
     * @param collection Bean集合
     * @param groups     验证组
     * @param <T>        Bean 泛型
     */
    public static <T> void valid(Validator validator, Collection<T> collection, Class<?>... groups) {
        valid(validator, collection, DEFAULT_PROCESS, groups);
    }

    /**
     * 迭代验证集合元素，使用自定义异常信息处理
     *
     * @param validator  验证器
     * @param collection Bean集合
     * @param process    异常信息处理
     * @param groups     验证组
     * @param <T>        Bean 泛型
     */
    public static <T> void valid(Validator validator, Collection<T> collection, ValidationResult process,
                                 Class<?>... groups) {
        if (CollectionUtils.isEmpty(collection)) {
            return;
        }
        int index = 0;
        Map<Integer, Set<ConstraintViolation<T>>> resultMap = new HashMap<>(collection.size());
        for (T obj : collection) {
            Set<ConstraintViolation<T>> violationSet;
            if (groups == null) {
                violationSet = validator.validate(obj);
            } else {
                violationSet = validator.validate(obj, groups);
            }
            if (!CollectionUtils.isEmpty(violationSet)) {
                resultMap.put(index, violationSet);
            }
            ++index;
        }
        if (process != null) {
            process.process(resultMap);
        }
    }


    public interface ValidationResult {
        /**
         * 批量验证结果处理接口，默认打印日志，抛出异常
         *
         * @param resultMap 验证结果
         * @param <T>       验证对象泛型
         */
        default <T> void process(Map<Integer, Set<ConstraintViolation<T>>> resultMap) {
            if (!CollectionUtils.isEmpty(resultMap)) {
                StringBuilder sb = new StringBuilder();
                resultMap.forEach((key, value) -> sb.append("index : ").append(key).append(" , error : ").append(
                        value.stream().map(item -> item.getPropertyPath().toString() + " " + item.getMessage())
                                .collect(Collectors.toList()).toString())
                        .append(";\n"));
                throw new BaseException(sb.toString());
            }
        }

        /**
         * 耽搁验证结果处理接口，默认打印日志，抛出异常
         *
         * @param resultSet 验证结果
         * @param <T>       验证对象泛型
         */
        default <T> void process(Set<ConstraintViolation<T>> resultSet) {
            if (!CollectionUtils.isEmpty(resultSet)) {
                throw new BaseException(resultSet
                        .stream()
                        .map(item -> item.getPropertyPath().toString() + " " + item.getMessage() + "; ")
                        .collect(Collectors.toList())
                        .toString());
            }
        }
    }
}
