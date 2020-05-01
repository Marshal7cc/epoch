package com.marshal.epoch.security.handler;


import com.alibaba.fastjson.JSON;
import com.marshal.epoch.common.constant.BaseConstant;
import com.marshal.epoch.security.component.JwtHeadFilter;
import com.marshal.epoch.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @Date: 2018/11/15
 * @Desc: 登录成功处理器
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements BaseConstant {

    @Autowired
    private RsaSigner signer;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_UTF8);
        String userJsonStr = JSON.toJSONString(authentication.getPrincipal());
        String token = JwtHelper.encode(userJsonStr, signer).getEncoded();

        Map tokenMap = new HashMap();
        tokenMap.put(JwtHeadFilter.EPOCH_TOKEN, token);
        //签发token
        response.getWriter().write(JSON.toJSONString(ResponseUtil.responseOk(tokenMap)));
    }


}
