package com.marshal.epoch.security.endpoints;

import com.marshal.epoch.security.exception.ValidateCodeException;
import com.marshal.epoch.security.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2020/1/28
 * @desc: 验证码生成
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaEndpoints {

    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("/create")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }
}
