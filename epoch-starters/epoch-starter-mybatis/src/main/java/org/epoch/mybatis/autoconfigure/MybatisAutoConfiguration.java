package org.epoch.mybatis.autoconfigure;

import com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.epoch.mybatis.domain.support.SnowflakeKeyGenerator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;

/**
 * @author Marshal
 * @since 2022/7/9
 */
@AutoConfigureBefore(IdentifierGeneratorAutoConfiguration.class)
public class MybatisAutoConfiguration {
    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new SnowflakeKeyGenerator();
    }
}
