package org.epoch.core.rest;

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
public class PageableData<T> {
    private long total;
    private List<T> rows;

    public PageableData() {
    }

    public PageableData(List<T> rows) {
        if (rows instanceof Page) {
            Page<T> page = (Page<T>) rows;
            this.rows = rows;
            this.total = page.getTotal();
        } else {
            this.rows = rows;
            this.total = rows.size();
        }
    }
}
