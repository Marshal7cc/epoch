package com.marshal.epoch.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @auth: Marshal
 * @date: 2019/8/25
 * @desc: 路由过滤器
 */
@Component
public class GatewayPreFilter extends ZuulFilter {

    private static final String filterType = "pre";

    /**
     * 过滤器类型
     * <p>
     * 1.pre 之前执行过滤
     * 2.post 之后执行过滤
     */
    @Override
    public String filterType() {
        return filterType;
    }

    /**
     * 优先级
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤规则
     * 返回任何值都表示继续执行
     * setSendZuulResponse表示停止执行
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //得到requestContext
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到Request域
        HttpServletRequest request = requestContext.getRequest();
        if ("OPTIONS".equals(request.getMethod()))
            return null;
        if (request.getRequestURL().indexOf("login") != -1)
            return null;
        /**
         * 一些敏感header会被过滤掉：
         * "Cookie", "Set-Cookie", "Authorization"
         * 1：可以在ZuulFilter中手动添加如下：
         * String token = request.getHeader("Authorization");
         * if (StringUtils.isNotBlank(token)) {
         *     requestContext.addZuulRequestHeader("Authorization", token);
         *  }
         *
         *  2：通过application.yml如下配置可以解决
         *
         *  zuul:
         *      sensitive-headers: Authorization
         */
        String token = request.getHeader("Epochen");
        if (StringUtils.isBlank(token)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(403);
            requestContext.setResponseBody("权限不足");
            requestContext.getResponse().setContentType("text/html;charset=utf-8");
        }
        /**
         * return null表示正常继续执行
         */
        return null;
    }
}
