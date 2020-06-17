package com.marshal.epoch.workflow.core.custom.db;

import java.sql.SQLException;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;

/**
 * <p>名称:CustomDbSqlSessionFactory</p>
 * <pre>
 * 描述:自定义CustomDbSqlSessionFactory
 * 解决当数据库表名区分大小写时，工作流相关的表名小写而导致的启动问题
 * 注：act默认找的是大写表名,且此操作仅可解决局部问题，暂时不能解决全局性问题
 * </pre>
 *
 * <pre>
 *     用法：
 *     com.marshal.epoch.workflow.config.ActivitiConfiguration
 *     springProcessEngineConfiguration.setDbSqlSessionFactory(customDbSqlSessionFactory());
 * </pre>
 *
 * @author Marshal Yuan
 * @link com.marshal.epoch.workflow.core.custom.db.CustomDbSqlSession
 * @since 2020/6/5
 */
public class CustomDbSqlSessionFactory extends DbSqlSessionFactory {

    /**
     * 当前数据库表名是否为大写
     */
    private boolean tableNameUpperCase;

    public CustomDbSqlSessionFactory(boolean tableNameUpperCase) {
        this.tableNameUpperCase = tableNameUpperCase;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        CustomDbSqlSession dbSqlSession = new CustomDbSqlSession(this, commandContext.getEntityCache());
        dbSqlSession.setTableNameUpperCase(tableNameUpperCase);
        if (getDatabaseSchema() != null && getDatabaseSchema().length() > 0) {
            try {
                dbSqlSession.getSqlSession().getConnection().setSchema(getDatabaseSchema());
            } catch (SQLException e) {
                throw new ActivitiException("Could not set database schema on connection", e);
            }
        }
        if (getDatabaseCatalog() != null && getDatabaseCatalog().length() > 0) {
            try {
                dbSqlSession.getSqlSession().getConnection().setCatalog(getDatabaseCatalog());
            } catch (SQLException e) {
                throw new ActivitiException("Could not set database catalog on connection", e);
            }
        }
        return dbSqlSession;
    }
}
