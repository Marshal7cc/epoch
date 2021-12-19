package org.epoch.starter.mybatis.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Marshal
 * @date : 2019/3/9
 */
public class EntityHelper {

    /**
     * 主键是否为空
     *
     * @param record
     * @return
     */
    public static boolean isPrimaryKeyNull(Object record) {
        for (Class<?> clazz = record.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    try {
                        Method method = clazz.getMethod("get" + StringUtils.capitalize(field.getName()));
                        if (method.invoke(record) == null) {
                            return true;
                        }
                    } catch (Exception e) {
                        //
                    }
                }
            }
        }
        return false;
    }
}
