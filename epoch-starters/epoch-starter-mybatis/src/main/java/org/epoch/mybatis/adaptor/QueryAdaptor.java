package org.epoch.mybatis.adaptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.data.domain.Sort;

/**
 * @author Marshal
 * @since 2022/7/7
 */
public class QueryAdaptor {
    private QueryAdaptor() {
        throw new UnsupportedOperationException();
    }

    public static <T> QueryWrapper<T> getQueryWrapper(Sort sort, Class<T> clazz) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        return null;
    }
}
