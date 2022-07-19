package org.epoch.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.epoch.core.exception.BaseException;
import org.epoch.core.exception.JsonConvertException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * @author Marshal
 * @date 2021/1/24
 */
public class TypeConverter implements ApplicationContextAware {

    private static ObjectMapper objectMapper;


    /**
     * List对象转换
     *
     * @param targetClazz 目标对象class
     * @param sourceList  源对象list
     * @param <T>         目标对象
     * @param <V>         源对象
     * @return 目标对象 List
     */
    public static <T, V> List<T> parseArray(Class<T> targetClazz, List<V> sourceList) {
        return parseArray(targetClazz, sourceList, null);
    }

    /**
     * List对象转换
     *
     * @param targetClazz 目标对象class
     * @param sourceList  源对象list
     * @param rule        自定义映射规则(target字段,source字段)
     * @param <T>         目标对象
     * @param <V>         源对象
     * @return 目标对象 List
     */
    public static <T, V> List<T> parseArray(Class<T> targetClazz, List<V> sourceList, Map<String, String> rule) {
        Assert.notNull(sourceList, "source convert list can't be null.");
        List<T> targetObjects = new ArrayList<>();
        sourceList.forEach(sourceObject -> {
            try {
                source2Target(targetClazz, rule, targetObjects, sourceObject);
            } catch (Exception e) {
                throw new BaseException("convert to {} error:", e, targetClazz.getName());
            }
        });
        return targetObjects;
    }

    private static <T, V> void source2Target(Class<T> targetClazz, Map<String, String> rule, List<T> targetObjects, V sourceObject) throws InstantiationException, IllegalAccessException {
        T target = targetClazz.newInstance();
        BeanUtils.copyProperties(sourceObject, target);
        // 复制自定义映射
        if (rule != null) {
            rule.forEach((targetField, sourceField) -> {
                try {
                    Field sourceObjField = sourceObject.getClass().getDeclaredField(sourceField);
                    sourceObjField.setAccessible(true);
                    Object value = sourceObjField.get(sourceObject);
                    Field targetObjField = target.getClass().getDeclaredField(targetField);
                    targetObjField.setAccessible(true);
                    targetObjField.set(target, value);
                } catch (Exception e) {
                    throw new BaseException("convert field error:", e, targetField);
                }
            });
        }
        targetObjects.add(target);
    }

    /**
     * List对象转换
     *
     * @param targetClazz 目标对象class
     * @param field       多语言字段名
     * @param sourceList  源对象list
     * @param <T>         目标对象
     * @param <V>         源对象
     * @return 目标对象 List
     */
    public static <T, V> List<T> parseArray(Class<T> targetClazz, String field, List<V> sourceList) {
        return parseArray(targetClazz, field, sourceList, null);
    }

    /**
     * description
     *
     * @param targetClazz 目标对象class
     * @param field       多语言字段名
     * @param sourceList  源对象list
     * @param rule        自定义映射规则(target字段,source字段)
     * @param <T>         目标对象
     * @param <V>         源对象
     * @return 目标对象 List
     */
    public static <T, V> List<T> parseArray(Class<T> targetClazz, String field, List<V> sourceList,
                                            Map<String, String> rule) {
        Assert.notNull(sourceList, "source convert list can't be null.");
        List<T> targetObjects = new ArrayList<>();
        sourceList.forEach(sourceObject -> {
            try {
                Field langField = sourceObject.getClass().getDeclaredField(field);
                langField.setAccessible(true);
                source2Target(targetClazz, rule, targetObjects, sourceObject);
            } catch (Exception e) {
                throw new BaseException("convert to {} error:", e, targetClazz.getName());
            }
        });
        return targetObjects;
    }


    /**
     * javaBean对象转换
     *
     * @param targetClazz  目标对象class
     * @param sourceObject 源对象
     * @param <T>          目标对象
     * @param <V>          源对象
     * @return 目标对象
     */
    public static <T, V> T parseObject(Class<T> targetClazz, V sourceObject) {
        Assert.notNull(sourceObject, "source convert object can't be null.");
        try {
            T targetObject = targetClazz.newInstance();
            BeanUtils.copyProperties(sourceObject, targetObject);
            return targetObject;
        } catch (Exception e) {
            throw new BaseException("convert to {} error:", e, targetClazz.getName());
        }
    }

    public static String toJSONString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        objectMapper = applicationContext.getBean(ObjectMapper.class);
        if (Objects.isNull(objectMapper)) {
            objectMapper = new ObjectMapper();
        }
    }
}
