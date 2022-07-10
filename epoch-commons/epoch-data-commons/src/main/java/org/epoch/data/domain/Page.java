package org.epoch.data.domain;

import java.util.List;

/**
 * 分页数据封装
 *
 * @author Marshal
 * @date 2019/8/27
 */
public class Page<T> {
    private PageInfo pageInfo;
    private List<T> content;

    public Page(List<T> content) {
        this.content = content;
    }

    public Page(PageInfo pageInfo, List<T> content) {
        this.content = content;
    }
}
