package com.marshal.epoch.generator.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marshal
 * @date 2019/5/18
 *  数据库表信息
 */
public class DbTable {
    private String name;
    private List<DbColumn> columns;

    public DbTable() {
        columns = new ArrayList<DbColumn>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DbColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumn> columns) {
        this.columns = columns;
    }

}
