package org.epoch.data.repository.query;

import org.epoch.data.domain.Page;
import org.epoch.data.domain.PageInfo;

/**
 * @author Marshal
 * @since 2022/7/7
 */
public class QueryHelper {
    private QueryHelper() {
        throw new UnsupportedOperationException();
    }

    public static <T> Page<T> getPage(org.springframework.data.domain.Page<T> page) {
        PageInfo pageInfo = PageInfo.builder().page(page.getNumber() + 1).size(page.getSize())
                .totalPages(page.getTotalPages()).totalElements(Integer.parseInt(String.valueOf(page.getTotalElements()))).build();
        return new Page<>(pageInfo, page.getContent());
    }
}
