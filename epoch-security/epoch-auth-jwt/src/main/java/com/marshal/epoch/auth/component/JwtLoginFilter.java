package com.marshal.epoch.auth.component;

import com.alibaba.fastjson.JSONObject;
import com.marshal.epoch.common.constant.BaseConstant;
import com.marshal.epoch.common.util.RequestHelper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auth: Marshal
 * @date: 2019/8/26
 * @desc: JwtLoginFilter
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter implements BaseConstant {

    private static final String LOGIN_URL = "/login";

    public JwtLoginFilter() {
        super(new AntPathRequestMatcher(LOGIN_URL, POST));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            JSONObject requestBody = RequestHelper.parseRequestBody(request);
            String userName = (String) requestBody.get("username");
            String password = (String) requestBody.get("password");
            //创建未认证的凭证(etAuthenticated(false)),注意此时凭证中的主体principal为用户名
            JwtLoginToken jwtLoginToken = new JwtLoginToken(userName, password);
            //将认证详情(ip,sessionId)写到凭证
            jwtLoginToken.setDetails(new WebAuthenticationDetails(request));
            //AuthenticationManager获取受支持的AuthenticationProvider(这里也就是JwtAuthenticationProvider),
            //生成已认证的凭证,此时凭证中的主体为userDetails
            Authentication authenticatedToken = this.getAuthenticationManager().authenticate(jwtLoginToken);
            return authenticatedToken;
        } catch (Exception e) {
            throw new BadCredentialsException("坏的凭证");
        }
    }

}
