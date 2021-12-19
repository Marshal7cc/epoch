package org.epoch.starter.mybatis.interceptor;

import static org.apache.ibatis.mapping.SqlCommandType.INSERT;

import java.time.LocalDateTime;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.epoch.starter.core.domain.User;
import org.epoch.starter.core.util.ReflectUtil;
import org.epoch.starter.core.util.RequestHelper;
import org.epoch.starter.mybatis.annotation.AuditDomain;
import org.epoch.starter.mybatis.domain.entity.BaseAuditEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据新增/更新拦截器,记录数据的更新、新增时间、修改/新增人
 * </p>
 *
 * @author Marshal
 * @date 2019/7/12
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class StandardDataInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof Executor) {

            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object param = invocation.getArgs()[1];

            // Effective while annotation @AuditDomain exists and is enabled.
            Class<?> clazz = param.getClass();
            boolean annotationPresent = clazz.isAnnotationPresent(AuditDomain.class);
            if (!annotationPresent || !clazz.getAnnotation(AuditDomain.class).enabled()) {
                return invocation.proceed();
            }

            BoundSql boundSql = mappedStatement.getBoundSql(param);

            // 当前认证用户信息
            User user = RequestHelper.getCurrentUser();
            Long userId = user.getUserId();

            ReflectUtil.setFieldValue(param, BaseAuditEntity.FILED_LAST_UPDATED_BY, String.valueOf(userId));
            ReflectUtil.setFieldValue(param, BaseAuditEntity.FILED_LAST_UPDATE_DATE, LocalDateTime.now());

            if (INSERT.equals(mappedStatement.getSqlCommandType())) {
                ReflectUtil.setFieldValue(param, BaseAuditEntity.FILED_CREATED_BY, String.valueOf(userId));
                ReflectUtil.setFieldValue(param, BaseAuditEntity.FILED_CREATED_DATE, LocalDateTime.now());
            }

            ReflectUtil.setFieldValue(boundSql, "parameterObject", param);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
