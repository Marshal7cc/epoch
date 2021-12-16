package org.epoch.security.service.impl;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.epoch.core.constants.BaseConstants;
import org.epoch.security.exception.ValidateCodeException;
import org.epoch.security.properties.EpochValidateCodeProperties;
import org.epoch.security.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @date 2020/1/28
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService, BaseConstants {

    /**
     * redis key
     */
    private static final String KEY_PREFIX = "epoch:cache:validate_code:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ValidateCodeException {
        String key = request.getParameter("validateCodeKey");
        if (StringUtils.isBlank(key)) {
            throw new ValidateCodeException("tip-validate code can not be null");
        }
        EpochValidateCodeProperties code = new EpochValidateCodeProperties();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        redisTemplate.opsForValue().set(KEY_PREFIX + key, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    @Override
    public void check(String key, String value) throws ValidateCodeException {
        String codeInRedis = redisTemplate.opsForValue().get(KEY_PREFIX + key);
        if (StringUtils.isBlank(value)) {
            throw new ValidateCodeException("tip-please enter validate code");
        }
        if (codeInRedis == null) {
            throw new ValidateCodeException("tip-validate code has been expired");
        }
        if (!StringUtils.equalsIgnoreCase(value, codeInRedis.trim())) {
            throw new ValidateCodeException("tip-incorrect validate code");
        }
    }

    private Captcha createCaptcha(EpochValidateCodeProperties code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), FileSuffix.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, FileSuffix.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
