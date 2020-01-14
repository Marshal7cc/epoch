package com.marshal.epoch.auth.component;

import com.alibaba.fastjson.JSON;
import com.marshal.epoch.core.constant.BaseConstant;
import com.marshal.epoch.auth.domain.CustomUserDetails;
import com.marshal.epoch.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2019/8/26
 * @desc: JwtHeadFilter
 */
@Component
public class JwtHeadFilter extends OncePerRequestFilter implements BaseConstant {

    public static final String EPOCH_TOKEN = "Epochen";

    private static final String HAVE_NO_TOKEN = "请先登录!";
    private static final String TOKEN_IS_EXPIRED = "token失效!";

    @Autowired
    private RsaVerifier verifier;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(EPOCH_TOKEN);
        if (token == null || token.isEmpty()) {
            response.setContentType(APPLICATION_JSON_UTF8);
            response.getWriter().write(JSON.toJSONString(ResponseUtil.responseErr(HAVE_NO_TOKEN)));
        }

        CustomUserDetails user;
        try {
            Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
            String claims = jwt.getClaims();
            user = JSON.parseObject(claims, CustomUserDetails.class);
            //todo: 可以在这里添加检查用户是否过期,冻结...
        } catch (Exception e) {
            //这里也可以filterChain.doFilter(request,response)然后return,那最后就会调用
            //.exceptionHandling().authenticationEntryPoint,也就是本列中的"需要登陆"
            response.setContentType(APPLICATION_JSON_UTF8);
            response.getWriter().write(JSON.toJSONString(ResponseUtil.responseErr(TOKEN_IS_EXPIRED)));
            return;
        }
        JwtLoginToken jwtLoginToken = new JwtLoginToken(user, "", user.getAuthorities());
        jwtLoginToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(jwtLoginToken);
        filterChain.doFilter(request, response);
    }

}
