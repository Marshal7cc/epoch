package com.marshal.epoch.generator.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/5/18
 * @desc: 数据库表信息
 */
public class DBTable {
    private String name;
    private List<DBColumn> columns;

    public DBTable() {
        columns = new ArrayList<DBColumn>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DBColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DBColumn> columns) {
        this.columns = columns;
    }

}
