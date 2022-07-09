package org.epoch.jpa.domain.support;

import java.io.Serializable;

import org.epoch.core.util.ApplicationContextHolder;
import org.epoch.snowflake.helper.SnowflakeHelper;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.ApplicationContext;

/**
 * @author Marshal
 * @since 2022/7/9
 */
public class SnowflakeKeyGenerator implements IdentifierGenerator {
    public static final String GENERATOR_NAME = "snowflakeKeyGenerator";
    public static final String GENERATOR_REFERENCE = "org.epoch.jpa.domain.support.SnowflakeKeyGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        ApplicationContext context = ApplicationContextHolder.getContext();
        SnowflakeHelper snowflakeHelper = context.getBean(SnowflakeHelper.class);
        return snowflakeHelper.next();
    }
}
