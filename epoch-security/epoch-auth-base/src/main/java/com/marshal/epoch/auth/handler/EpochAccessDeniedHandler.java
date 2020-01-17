package com.marshal.epoch.auth.handler;

import com.alibaba.fastjson.JSON;
import com.marshal.epoch.core.constant.BaseConstant;
import com.marshal.epoch.core.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2020/1/15
 * @desc: 权限不足处理器
 */
@Component
public class EpochAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.getWriter().write(JSON.toJSONString(ResponseUtil.responseErr(BaseConstant.UN_AUTHORIZED, "not full authenticated to visit the resource")));
    }

}
