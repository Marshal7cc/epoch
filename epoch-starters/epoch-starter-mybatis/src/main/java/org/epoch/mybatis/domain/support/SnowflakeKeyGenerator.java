package org.epoch.mybatis.domain.support;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.epoch.core.util.ApplicationContextHelper;
import org.epoch.snowflake.helper.SnowflakeHelper;
import org.springframework.context.ApplicationContext;

/**
 * @author Marshal
 * @since 2022/7/9
 */
public class SnowflakeKeyGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        ApplicationContext context = ApplicationContextHelper.getContext();
        SnowflakeHelper snowflakeHelper = context.getBean(SnowflakeHelper.class);
        return snowflakeHelper.next();
    }
}
