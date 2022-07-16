package org.epoch.mybatis.autoconfigure;

import com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.epoch.mybatis.domain.support.AuditorAwareInterceptor;
import org.epoch.mybatis.domain.support.SnowflakeKeyGenerator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

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

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    @Bean
    public AuditorAwareInterceptor auditorAwareInterceptor(AuditorAware<String> auditorAware) {
        return new AuditorAwareInterceptor(auditorAware);
    }
}
