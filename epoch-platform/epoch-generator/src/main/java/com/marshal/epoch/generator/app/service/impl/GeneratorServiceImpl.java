package com.marshal.epoch.generator.app.service.impl;

import com.marshal.epoch.core.exception.CommonException;
import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.generator.app.service.GeneratorService;
import com.marshal.epoch.generator.app.generator.AbstractGenerator;
import com.marshal.epoch.generator.api.dto.DbColumn;
import com.marshal.epoch.generator.api.dto.DbTable;
import com.marshal.epoch.generator.api.dto.GeneratorConfig;
import com.marshal.epoch.generator.infra.enums.FileType;
import com.marshal.epoch.generator.infra.util.DbUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.Validate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * @author Marshal
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    private static final String REMARKS = "remarks";

    private Map<FileType, AbstractGenerator> generatorMap = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<String> showTables() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<String> tables;

            Connection conn = DbUtil.getConnectionBySqlSession(sqlSession);
            tables = DbUtil.showAllTables(conn);
            conn.close();
            return tables;
        } catch (SQLException e) {
            logger.error("数据库查询出错");
        }
        return new ArrayList<>();
    }

    @Override
    public void generatorFile(GeneratorConfig info, HttpServletResponse response) {
        Validate.isTrue(!StringUtils.isAnyBlank(info.getParentPackagePath(), info.getPackagePath(), info.getTableName(), info.getEntityName()), "请将信息填写完整!");

        String tableName = info.getTableName();

        DbTable dbTable = getTableInfo(tableName);
        try {
            createFile(dbTable, info, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 获取table信息
     *
     * @param tableName
     * @return
     */
    private DbTable getTableInfo(String tableName) {
        Connection conn = null;
        DbTable dbTable = new DbTable();
        List<DbColumn> columns = dbTable.getColumns();
        List<String> notNullColumns = null;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 设置tablename
            dbTable.setName(tableName);
            conn = DbUtil.getConnectionBySqlSession(sqlSession);
            DatabaseMetaData dbmd = conn.getMetaData();
            // 获取主键字段
            String columnPk = DbUtil.getPrimaryKey(tableName, dbmd, conn.getCatalog());
            // 获取不为空的字段
            notNullColumns = DbUtil.getNotNullColumn(tableName, dbmd, conn.getCatalog());
            // 获取表列信息
            ResultSet rs1 = DbUtil.getTableColumnInfo(tableName, dbmd, conn.getCatalog());

            while (rs1.next()) {
                String columnName = rs1.getString("COLUMN_NAME");
                if ("object_version".equalsIgnoreCase(columnName) || "creation_date".equalsIgnoreCase(columnName)
                        || "created_by".equalsIgnoreCase(columnName)
                        || "last_update_date".equalsIgnoreCase(columnName)
                        || "last_updated_by".equalsIgnoreCase(columnName)) {
                    continue;
                }
                columns.add(setColumnInfo(rs1, columnPk, notNullColumns));
            }
            rs1.close();
            conn.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return dbTable;
    }

    private DbColumn setColumnInfo(ResultSet rs1, String columnPk, List<String> notNullColumns) throws SQLException {
        DbColumn column = new DbColumn();
        String columnName = rs1.getString("COLUMN_NAME");
        column.setName(columnName);
        String typeName = rs1.getString("TYPE_NAME");
        column.setJdbcType(typeName);
        if (StringUtils.isNotEmpty(rs1.getString(REMARKS))) {
            column.setRemarks(rs1.getString(REMARKS));
        }
        // 判断是否为主键
        if (columnName.equalsIgnoreCase(columnPk)) {
            column.setId(true);
        }
        // 判断是否为null字段
        for (String n : notNullColumns) {
            if (columnName.equalsIgnoreCase(n) && !columnName.equalsIgnoreCase(columnPk)) {
                if ("BIGINT".equalsIgnoreCase(typeName)) {
                    column.setNotNull(true);
                } else if ("VARCHAR".equalsIgnoreCase(typeName)) {
                    column.setNotEmpty(true);
                }
            }
        }

        column.setColumnLength(rs1.getString("COLUMN_SIZE"));
        return column;
    }

    private void createFile(DbTable table, GeneratorConfig info, HttpServletResponse response) throws Exception {
        Map<String, byte[]> files = new HashMap<>(10);

        for (FileType type : FileType.values()) {
            String entityName = info.getEntityName();
            byte[] bytes = generatorMap.get(type).generate(table, info);
            if (bytes != null) {
                files.put(entityName + type.getFileSuffix(), bytes);
            }
        }

        if (files.size() > 0) {
            Response.downloadZip(files, info.getEntityName() + ".zip", response);
        }
    }


    @PostConstruct
    public void init() {
        Map<String, AbstractGenerator> beans = applicationContext.getBeansOfType(AbstractGenerator.class);
        beans.forEach((k, v) -> {
            generatorMap.put(v.getFileType(), v);
        });
    }
}
