package org.epoch.data.domain;

import java.util.List;

import lombok.Data;

/**
 * 分页数据封装
 *
 * @author Marshal
 * @date 2019/8/27
 */
@Data
public class Page<T> {
    private PageInfo pageInfo;
    private List<T> content;

    public Page(PageInfo pageInfo, List<T> content) {
        this.pageInfo = pageInfo;
        this.content = content;
    }
}
