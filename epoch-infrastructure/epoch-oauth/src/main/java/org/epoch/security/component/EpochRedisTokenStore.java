package org.epoch.security.component;

import com.alibaba.fastjson.JSON;
import org.epoch.security.entity.OauthAccessToken;
import org.epoch.security.service.OauthAccessTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义TokenStore, 获取token后将token同时存入数据库和redis
 *
 * @author Marshal
 * @date 2019/3/6
 */
@Component
@Slf4j
public class EpochRedisTokenStore extends RedisTokenStore {

    private final static String REDIS_CATALOG = "epoch:cache:oauth2_token:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OauthAccessTokenRepository oauthAccessTokenService;

    public EpochRedisTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = super.readAccessToken(tokenValue);
        return accessToken;
    }

    @Override
    public void removeAccessToken(String tokenValue) {
        redisTemplate.delete(REDIS_CATALOG + tokenValue);
        super.removeAccessToken(tokenValue);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        super.storeAccessToken(token, authentication);

        // 自定义操作
        String clientId = authentication.getOAuth2Request().getClientId();
        OauthAccessToken oauthAccessToken = new OauthAccessToken();
        Object principal = authentication.getPrincipal();
        if (principal instanceof EpochUserDetails) {
            EpochUserDetails details = (EpochUserDetails) principal;
            oauthAccessToken.setUserId(details.getUserId());
        }
        oauthAccessToken.setToken(token.getValue());
        oauthAccessToken.setClientId(authentication.getOAuth2Request().getClientId());
        oauthAccessToken.setTokenAccessType(authentication.getOAuth2Request().getGrantType());
        // token获取时间和失效时间
        Calendar expiration = Calendar.getInstance();
        expiration.setTime(token.getExpiration());
        expiration.add(Calendar.SECOND, -token.getExpiresIn());
        oauthAccessToken.setTokenAccessTime(expiration.getTime());
        oauthAccessToken.setTokenExpiresTime(token.getExpiration());
        oauthAccessTokenService.submit(oauthAccessToken);

        redisTemplate.opsForValue().set(REDIS_CATALOG + token.getValue(), JSON.toJSONString(token),
                token.getExpiresIn(), TimeUnit.SECONDS);
    }
}
