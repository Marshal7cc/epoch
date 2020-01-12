package com.marshal.epoch.generator.dto;

/**
 * 用于生成Mapper.xml
 * 记录每一列的属性
 */
public class XmlColumnsInfo {
    private String tableColumnsName;
    private String dBColumnsName;
    private String jdbcType;

    public String getTableColumnsName() {
        return tableColumnsName;
    }

    public void setTableColumnsName(String tableColumnsName) {
        this.tableColumnsName = tableColumnsName;
    }

    public String getdBColumnsName() {
        return dBColumnsName;
    }

    public void setdBColumnsName(String dBColumnsName) {
        this.dBColumnsName = dBColumnsName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}