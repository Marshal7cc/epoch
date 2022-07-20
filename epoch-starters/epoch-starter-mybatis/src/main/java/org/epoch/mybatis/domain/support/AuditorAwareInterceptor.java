package org.epoch.mybatis.domain.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.epoch.data.domain.Auditable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;

/**
 * @author Marshal
 * @since 2022/7/9
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class AuditorAwareInterceptor implements Interceptor {
    private static final String ENTITY = "et";
    private static final String ENTITY_WRAPPER = "ew";

    private AuditorAware<String> auditorAware;

    public AuditorAwareInterceptor(AuditorAware<String> auditorAware) {
        this.auditorAware = auditorAware;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof Executor) {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
                resolveInsert(invocation);
            } else if (SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
                resolveUpdate(invocation);
            }
        }
        return invocation.proceed();
    }

    private void resolveInsert(Invocation invocation) throws IllegalAccessException {
        Object entity = invocation.getArgs()[1];
        if (entity == null) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        for (Class<?> clazz = entity.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                setIfAbsent(entity, field, CreatedBy.class, getUserId());
                setIfAbsent(entity, field, CreatedDate.class, now);
                setIfAbsent(entity, field, LastModifiedBy.class, getUserId());
                setIfAbsent(entity, field, LastModifiedDate.class, now);
            }
        }
    }

    private void resolveUpdate(Invocation invocation) throws IllegalAccessException {
        Object arg = invocation.getArgs()[1];
        Object entity = null;
        Object wrapper = null;
        if (arg instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) arg;
            Set<String> set = paramMap.keySet();
            if (set.contains(ENTITY)) {
                entity = paramMap.get(ENTITY);
            }
        }

        // Situation: use entity
        if (entity != null) {
            LocalDateTime now = LocalDateTime.now();
            for (Class<?> clazz = entity.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    setIfAbsent(entity, field, LastModifiedBy.class, getUserId());
                    setIfAbsent(entity, field, LastModifiedDate.class, now);
                }
            }
        } else {
            // Situation: Only use UpdateWrapper
            if (arg instanceof MapperMethod.ParamMap) {
                if (!((MapperMethod.ParamMap<?>) arg).containsKey(ENTITY_WRAPPER)) {
                    return;
                }
                wrapper = ((MapperMethod.ParamMap<?>) arg).get(ENTITY_WRAPPER);
                ((UpdateWrapper) wrapper).set(Auditable.FIELD_UPDATED_BY, getUserId());
                ((UpdateWrapper) wrapper).set(Auditable.FIELD_UPDATED_DATE, LocalDateTime.now());
            }
        }
    }

    private void setIfAbsent(Object entity, Field field, Class<? extends Annotation> clazz, Object value) throws IllegalAccessException {
        if (!field.isAnnotationPresent(clazz)) {
            return;
        }
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        if (field.get(entity) == null) {
            field.set(entity, value);
        }
        if (!accessible) {
            field.setAccessible(false);
        }
    }

    private String getUserId() {
        if (auditorAware == null) {
            return null;
        }
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();
        if (!currentAuditor.isPresent()) {
            return null;
        }
        return auditorAware.getCurrentAuditor().get();
    }

}
