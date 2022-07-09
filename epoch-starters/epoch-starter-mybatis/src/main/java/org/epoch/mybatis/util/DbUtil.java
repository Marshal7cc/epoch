package org.epoch.mybatis.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

/**
 * @author Marshal
 * @date 2019/6/26
 */
public class DbUtil {

    private DbUtil() {
    }

    private static final String COLUMN_NAME = "COLUMN_NAME";

    public String getPrimaryKey(SqlSession sqlSession, String tableName) throws SQLException {
        Connection connection = sqlSession.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet rs = databaseMetaData.getPrimaryKeys(null, null, tableName);
        String primaryKey = null;
        while (rs.next()) {
            primaryKey = rs.getString(COLUMN_NAME);
        }
        return primaryKey;
    }
}
