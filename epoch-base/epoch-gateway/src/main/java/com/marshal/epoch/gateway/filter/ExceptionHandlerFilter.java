package com.marshal.epoch.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

/**
 * @auth: Marshal
 * @date: 2020/1/26
 * @desc:
 */
@Component
public class ExceptionHandlerFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

    private static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

    public static final String DEFAULT_ERR_MSG = "system error occur, please contact web admin";

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // only forward to errorPath if it hasn't been forwarded to already
        return ctx.getThrowable() != null
                && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String errMsg = DEFAULT_ERR_MSG;
        try {
            HttpServletRequest request = ctx.getRequest();
            Throwable e = (Exception) ctx.getThrowable();
            Throwable re = getOriginException(e);
            if (re instanceof java.net.ConnectException) {
                errMsg = "Real Service Connection refused";
                log.warn("uri:{},error:{}", request.getRequestURI(), re.getMessage());
            } else if (re instanceof java.net.SocketTimeoutException) {
                errMsg = "Real Service Timeout";
                log.warn("uri:{},error:{}", request.getRequestURI(), re.getMessage());
            } else if (re instanceof com.netflix.client.ClientException) {
                errMsg = re.getMessage();
                log.warn("uri:{},error:{}", request.getRequestURI(), re.getMessage());
            } else {
                log.warn("Error during filtering", e);
            }

            HttpServletResponse response = ctx.getResponse();
            handResponse(response, errMsg);
        } catch (Exception e) {
            String error = "Error during filtering [ExceptionHandlerFilter]";
            HttpServletResponse response = ctx.getResponse();
            handResponse(response, error);
        }
        return null;
    }

    private Throwable getOriginException(Throwable e) {
        e = e.getCause();
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e;
    }

    private void handResponse(HttpServletResponse response, String msg) {
        response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            log.error("response error in Exception handler");
        }
        writer.write(msg);
        writer.close();
    }
}
