package org.epoch.data.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author Marshal
 * @since 2022/7/10
 */
@Data
@Builder
public class PageInfo {
    private Integer page;
    private Integer size;
    private Integer totalElements;
    private Integer totalPages;

    public long getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) getSize());
    }
}
