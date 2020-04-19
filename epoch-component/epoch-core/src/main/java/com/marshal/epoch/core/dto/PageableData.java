package com.marshal.epoch.core.dto;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/8/27
 * @desc: 分页数据封装
 */
public class PageableData {

    /**
     * 返回记录总数
     */
    private long total;

    /**
     * 数据列表
     */
    private List<?> rows;

    public PageableData(List<?> rows) {
        if (rows instanceof Page) {
            Page<?> page = (Page<?>) rows;
            this.rows = rows;
            this.total = page.getTotal();
        } else {
            this.rows = rows;
            this.total = rows.size();
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
