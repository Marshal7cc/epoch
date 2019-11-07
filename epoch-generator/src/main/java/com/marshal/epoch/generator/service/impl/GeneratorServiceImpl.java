package com.marshal.epoch.generator.service.impl;

import com.marshal.epoch.core.util.ResponseUtil;
import com.marshal.epoch.generator.dto.DBColumn;
import com.marshal.epoch.generator.dto.DBTable;
import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.service.GeneratorService;
import com.marshal.epoch.generator.util.DBUtil;
import com.marshal.epoch.generator.util.FileUtil;
import com.marshal.epoch.generator.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Service
public class GeneratorServiceImpl implements GeneratorService {

    /**
     * 文件生成行为
     */
    private static final String NOT_OPERATION = "NotOperation";
    private static final String CREATE = "create";

    @Autowired
    @Qualifier("sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<String> showTables() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<String> tables;

            Connection conn = DBUtil.getConnectionBySqlSession(sqlSession);
            tables = DBUtil.showAllTables(conn);
            conn.close();
            return tables;
        } catch (SQLException e) {
            logger.error("数据库查询出错");
        }
        return new ArrayList<>();
    }

    @Override
    public void generatorFile(GeneratorConfig info, HttpServletResponse response) throws Exception {
        if (StringUtils.isAnyBlank(info.getProjectPath(), info.getParentPackagePath(), info.getPackagePath(), info.getTableName(), info.getTargetName())) {
            throw new Exception("请将信息填写完整!");
        }
        String tableName = info.getTableName();
        String targetName = info.getTargetName();
        info.setDtoName(targetName + ".java");
        info.setControllerName(targetName + "Controller.java");
        info.setServiceName(targetName + "Service.java");
        info.setImplName(targetName + "ServiceImpl.java");
        info.setMapperName(targetName + "Mapper.java");
        info.setMapperXmlName(targetName + "Mapper.xml");
        DBTable dbTable = getTableInfo(tableName);
        try {
            createFile(dbTable, info, response);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * 获取table信息
     *
     * @param tableName
     * @return
     */
    private DBTable getTableInfo(String tableName) {
        Connection conn = null;
        DBTable dbTable = new DBTable();
        List<DBColumn> columns = dbTable.getColumns();
        List<String> NotNullColumns = null;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 设置tablename
            dbTable.setName(tableName);
            conn = DBUtil.getConnectionBySqlSession(sqlSession);
            DatabaseMetaData dbmd = conn.getMetaData();
            // 获取主键字段
            String columnPk = DBUtil.getPrimaryKey(tableName, dbmd, conn.getCatalog());
            // 获取不为空的字段
            NotNullColumns = DBUtil.getNotNullColumn(tableName, dbmd, conn.getCatalog());
            // 获取表列信息
            ResultSet rs1 = DBUtil.getTableColumnInfo(tableName, dbmd, conn.getCatalog());

            while (rs1.next()) {
                String columnName = rs1.getString("COLUMN_NAME");
                if ("object_version".equalsIgnoreCase(columnName) || "create_time".equalsIgnoreCase(columnName) || "update_time".equalsIgnoreCase(columnName)) {
                    continue;
                }
                columns.add(setColumnInfo(rs1, columnPk, NotNullColumns));
            }
            rs1.close();
            conn.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return dbTable;
    }

    private DBColumn setColumnInfo(ResultSet rs1, String columnPk, List<String> NotNullColumns) throws SQLException {
        DBColumn column = new DBColumn();
        String columnName = rs1.getString("COLUMN_NAME");
        column.setName(columnName);
        String typeName = rs1.getString("TYPE_NAME");
        column.setJdbcType(typeName);
        if (StringUtils.isNotEmpty(rs1.getString("REMARKS"))) {
            column.setRemarks(rs1.getString("REMARKS"));
        }
        // 判断是否为主键
        if (columnName.equalsIgnoreCase(columnPk)) {
            column.setId(true);
        }
        // 判断是否为null字段
        for (String n : NotNullColumns) {
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

    private void createFile(DBTable table, GeneratorConfig info, HttpServletResponse response) throws IOException {

        int rs = FileUtil.isFileExist(info);
        if (rs == 0) {
            Map<String, byte[]> files = new HashMap<>();
            if (!NOT_OPERATION.equalsIgnoreCase(info.getDtoStatus())) {
                byte[] bytes = FileUtil.createDto(table, info);
                if (bytes != null) {
                    files.put(info.getDtoName(), bytes);
                }
            }
            if (!NOT_OPERATION.equalsIgnoreCase(info.getControllerStatus())) {
                byte[] bytes = FileUtil.createFtlInfoByType(FileUtil.pType.Controller, table, info);
                if (bytes != null) {
                    files.put(info.getControllerName(), bytes);
                }
            }
            if (!NOT_OPERATION.equalsIgnoreCase(info.getMapperStatus())) {
                byte[] bytes = FileUtil.createFtlInfoByType(FileUtil.pType.Mapper, table, info);
                if (bytes != null) {
                    files.put(info.getMapperName(), bytes);
                }
            }
            if (!NOT_OPERATION.equalsIgnoreCase(info.getImplStatus())) {
                byte[] bytes = FileUtil.createFtlInfoByType(FileUtil.pType.Impl, table, info);
                if (bytes != null) {
                    files.put(info.getImplName(), bytes);
                }
            }
            if (!NOT_OPERATION.equalsIgnoreCase(info.getServiceStatus())) {
                byte[] bytes = FileUtil.createFtlInfoByType(FileUtil.pType.Service, table, info);
                if (bytes != null) {
                    files.put(info.getServiceName(), bytes);
                }
            }
            if (!NOT_OPERATION.equalsIgnoreCase(info.getMapperXmlStatus())) {
                byte[] bytes = FileUtil.createMapperXml(table, info);
                if (bytes != null) {
                    files.put(info.getMapperXmlName(), bytes);
                }
            }
            if (files.size() > 0) {
                ResponseUtil.responseZip(files, info.getTargetName() + ".zip", response);
            }
        }
    }

}
