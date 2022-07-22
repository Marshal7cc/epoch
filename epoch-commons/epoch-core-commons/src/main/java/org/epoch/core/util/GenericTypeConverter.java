package org.epoch.core.util;

import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.compress.utils.Lists;
import org.epoch.core.exception.JsonProcessingException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author Marshal
 * @date 2021/1/24
 */
public class GenericTypeConverter {

    private static ObjectMapper objectMapper;

    public GenericTypeConverter(ObjectMapper objectMapper) {
        GenericTypeConverter.objectMapper = objectMapper;
    }

    /**
     * @param clazz  target clazz
     * @param source source list
     * @param <D>    generic type of targetObj
     * @param <S>    generic type of sourceObj
     * @return
     */
    public static <D, S> List<D> parseArray(List<S> source, Class<D> clazz) {
        Assert.notNull(source, "source object can't be null.");
        if (CollectionUtils.isEmpty(source)) {
            return Lists.newArrayList();
        }
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(toJSONString(source), type);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new JsonProcessingException("convert obj to {} error:", clazz.getName());
        }
    }


    /**
     * Convert obj to another obj of different clazz.
     *
     * @param clazz  targetObj clazz
     * @param source sourceObj
     * @param <D>    generic type of targetObj
     * @param <S>    generic type of sourceObj
     * @return targetObj
     */
    public static <D, S> D parseObject(S source, Class<D> clazz) {
        Assert.notNull(source, "source object can't be null.");
        try {
            return objectMapper.readValue(toJSONString(source), clazz);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new JsonProcessingException("convert obj to {} error:", clazz.getName());
        }
    }

    /**
     * Convert object to json string.
     *
     * @param obj object
     * @return json string
     */
    public static String toJSONString(Object obj) {
        Assert.notNull(obj, "source object can't be null.");
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new JsonProcessingException("convert obj to json string error.");
        }
    }
}
