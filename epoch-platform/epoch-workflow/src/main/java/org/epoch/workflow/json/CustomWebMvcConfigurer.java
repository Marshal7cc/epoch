package org.epoch.workflow.json;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auth: Marshal
 * @date: 2019/2/5
 * @desc: 扩展spring mvc 消息转换器 ,转换activiti返回数据格式
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.forEach(item -> {
            if (item instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter) item).setObjectMapper(new CustomObjectMapper());
            }
        });
    }
}
