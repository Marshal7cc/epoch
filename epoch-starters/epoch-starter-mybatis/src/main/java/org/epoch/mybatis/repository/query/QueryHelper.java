package org.epoch.mybatis.repository.query;

import java.util.Iterator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.epoch.data.domain.Page;
import org.epoch.data.domain.PageInfo;
import org.springframework.data.domain.Sort;

/**
 * @author Marshal
 * @since 2022/7/7
 */
public class QueryHelper {
    private QueryHelper() {
        throw new UnsupportedOperationException();
    }

    public static <T> QueryWrapper<T> getQueryWrapper(Sort sort) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Iterator<Sort.Order> iterator = sort.iterator();
        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            if (Sort.Direction.ASC.equals(order.getDirection())) {
                queryWrapper.orderByAsc(order.getProperty());
            } else {
                queryWrapper.orderByDesc(order.getProperty());
            }
        }
        return queryWrapper;
    }

    public static <T> Page<T> getPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page) {
        PageInfo pageInfo = PageInfo.builder()
                .page(Integer.parseInt(String.valueOf(page.getCurrent())))
                .size(Integer.parseInt(String.valueOf(page.getSize())))
                .totalPages(Integer.parseInt(String.valueOf(page.getPages())))
                .totalElements(Integer.parseInt(String.valueOf(page.getTotal()))).build();
        return new Page<>(pageInfo, page.getRecords());
    }

}
