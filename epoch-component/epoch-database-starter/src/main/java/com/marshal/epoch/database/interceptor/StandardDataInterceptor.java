package com.marshal.epoch.database.interceptor;

import com.marshal.epoch.common.dto.AuthenticationUser;
import com.marshal.epoch.common.dto.BaseDto;
import com.marshal.epoch.common.util.ReflectUtil;
import com.marshal.epoch.common.util.RequestHelper;
import com.marshal.epoch.database.annotation.StdWho;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

import static org.apache.ibatis.mapping.SqlCommandType.INSERT;

/**
 * @auth: Marshal
 * @date: 2019/7/12
 * @desc: 数据新增/更新拦截器,记录数据的更新、新增时间、修改/新增人
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class StandardDataInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof Executor) {

            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object param = invocation.getArgs()[1];

            //当实体类引入@StdWho注解并且enable设置为false时，跳过本拦截器
            Class<?> clazz = param.getClass();
            boolean isStdWhoPresent = clazz.isAnnotationPresent(StdWho.class);
            if (isStdWhoPresent && !clazz.getAnnotation(StdWho.class).enabled()) {
                return invocation.proceed();
            }

            BoundSql boundSql = mappedStatement.getBoundSql(param);

            //当前认证用户信息
            AuthenticationUser user = RequestHelper.getCurrentUser();
            Long userId = user.getUserId();

            ReflectUtil.setFieldValue(param, BaseDto.FILED_LAST_UPDATED_BY, userId);
            ReflectUtil.setFieldValue(param, BaseDto.FILED_LAST_UPDATE_DATE, new Date());

            if (INSERT.equals(mappedStatement.getSqlCommandType())) {
                ReflectUtil.setFieldValue(param, BaseDto.FILED_CREATED_BY, userId);
                ReflectUtil.setFieldValue(param, BaseDto.FILED_CREATION_DATE, new Date());
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
