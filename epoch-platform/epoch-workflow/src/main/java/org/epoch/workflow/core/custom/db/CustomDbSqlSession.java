package org.epoch.workflow.core.custom.db;

import java.sql.Connection;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.persistence.cache.EntityCache;

/**
 * <p>名称:CustomDbSqlSession</p>
 *
 * @author Marshal Yuan
 * @since 2020/6/5
 */
public class CustomDbSqlSession extends DbSqlSession {

    private boolean tableNameUpperCase;

    public CustomDbSqlSession(DbSqlSessionFactory dbSqlSessionFactory, EntityCache entityCache) {
        super(dbSqlSessionFactory, entityCache);
    }

    public CustomDbSqlSession(DbSqlSessionFactory dbSqlSessionFactory, EntityCache entityCache, Connection connection, String catalog, String schema) {
        super(dbSqlSessionFactory, entityCache, connection, catalog, schema);
    }

    @Override
    public boolean isTablePresent(String tableName) {
        return super.isTablePresent(this.tableNameUpperCase ? tableName : tableName.toLowerCase());
    }

    public boolean getTableNameUpperCase() {
        return tableNameUpperCase;
    }

    public void setTableNameUpperCase(boolean tableNameUpperCase) {
        this.tableNameUpperCase = tableNameUpperCase;
    }
}
