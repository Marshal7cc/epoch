package org.epoch.core.convert.config;


import org.epoch.core.convert.date.DateConverter;
import org.epoch.core.convert.date.LocalDateConverter;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${epoch.date.converter.enable:true}")
    private boolean enable;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        if (enable) {
            registry.addConverter(new DateConverter());
            registry.addConverter(new LocalDateConverter());
        }
    }
}
