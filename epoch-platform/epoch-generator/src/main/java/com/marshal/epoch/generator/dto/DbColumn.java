package com.marshal.epoch.generator.dto;

/**
 * @author Marshal
 * @date 2019/5/18
 *  数据库列信息
 */
public class DbColumn {

    private String name;

    private boolean isId = false;

    private boolean isNotEmpty = false;

    private boolean isNotNull = false;

    private String javaType;

    private String jdbcType;

    private String columnLength;

    private String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public boolean isNotEmpty() {
        return isNotEmpty;
    }

    public void setNotEmpty(boolean notEmpty) {
        isNotEmpty = notEmpty;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public void setNotNull(boolean notNull) {
        isNotNull = notNull;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }
}
