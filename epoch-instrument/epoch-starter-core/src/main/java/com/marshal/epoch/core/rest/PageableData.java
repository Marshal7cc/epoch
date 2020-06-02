package com.marshal.epoch.core.rest;

import java.util.List;

import com.github.pagehelper.Page;
import lombok.Data;

/**
 * 分页数据封装
 *
 * @author Marshal
 * @date 2019/8/27
 */
@Data
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
}
