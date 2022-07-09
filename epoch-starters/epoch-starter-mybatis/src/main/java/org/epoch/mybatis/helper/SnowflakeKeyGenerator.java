package org.epoch.mybatis.helper;

import org.epoch.core.util.ApplicationContextHolder;
import org.epoch.snowflake.helper.SnowflakeHelper;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.genid.GenId;

/**
 * @author Marshal
 * @date 2021/3/5
 */
public class SnowflakeKeyGenerator implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        ApplicationContext context = ApplicationContextHolder.getContext();
        SnowflakeHelper snowflakeHelper = context.getBean(SnowflakeHelper.class);
        return String.valueOf(snowflakeHelper.next());
    }
}
