package org.epoch.starter.core.convert.config;


import org.epoch.starter.core.convert.date.DateConverter;
import org.epoch.starter.core.convert.date.LocalDateConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * 日期格式转换配置类
 * </p>
 *
 * @author Marshal
 */
public class ConvertWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
        registry.addConverter(new LocalDateConverter());
    }
}
