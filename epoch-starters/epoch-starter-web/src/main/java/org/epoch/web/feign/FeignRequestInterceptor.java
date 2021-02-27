package org.epoch.web.feign;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * feign请求拦截，保留请求头信息
 *
 * @author Marshal
 * @date 2020/3/22
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                template.header(name, values);
            }
            logger.info("feign interceptor header:{}", template);
        }
    }
}

