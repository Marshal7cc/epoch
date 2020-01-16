package com.marshal.epoch.auth.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.epoch.auth.entity.OauthAccessToken;
import com.marshal.epoch.auth.service.OauthAccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @auth: Marshal
 * @date: 2019/3/6
 * @desc: 自定义TokenStore, 获取token后将token同时存入数据库和redis
 */
@Component
@Slf4j
public class EpochRedisTokenStore extends RedisTokenStore {

    public final static String REDIS_CATALOG = "epoch:cache:oauth2_token:";
    public final static String REDIS_CATALOG_AUTHENTICATION = "epoch:cache:oauth2_authentication:";

    @Autowired
    private OauthAccessTokenService oauthAccessTokenService;

    @Autowired
    StringRedisTemplate redisTemplate;

    public EpochRedisTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        String tokenState = redisTemplate.opsForValue().get(REDIS_CATALOG + tokenValue);
        Map map = JSONObject.parseObject(tokenState, Map.class);

        if (StringUtils.isNotEmpty(tokenState)) {
            DefaultOAuth2AccessToken accessToken = JSON.parseObject(tokenState, DefaultOAuth2AccessToken.class);
//            accessToken.setValue(String.valueOf(map.get("token")));
//            accessToken.setTokenType(String.valueOf(map.get("tokenAccessType")));
//            Set<String> set = new HashSet<>();
//            set.add("all");
//            accessToken.setScope(set);
//            accessToken.setExpiration(new Date((Long) map.get("tokenExpiresTime")));
            return accessToken;
        }
        return null;
    }

    @Override
    public void removeAccessToken(String tokenValue) {
        redisTemplate.delete(REDIS_CATALOG + tokenValue);
//        oauthAccessTokenService.revokeToken(token.getValue());
        super.removeAccessToken(tokenValue);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
//        super.storeAccessToken(token, authentication);
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
        //token获取时间和失效时间
        Calendar expiration = Calendar.getInstance();
        expiration.setTime(token.getExpiration());
        expiration.add(Calendar.SECOND, -token.getExpiresIn());
        oauthAccessToken.setTokenAccessTime(expiration.getTime());
        oauthAccessToken.setTokenExpiresTime(token.getExpiration());
        oauthAccessTokenService.submit(oauthAccessToken);
        String tokenString = "";
        try {
            tokenString = new ObjectMapper().writeValueAsString(oauthAccessToken);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        redisTemplate.opsForValue().set(REDIS_CATALOG + token.getValue(), JSON.toJSONString(token), token.getExpiresIn(),
                TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(REDIS_CATALOG_AUTHENTICATION + token.getValue(), JSON.toJSONString(authentication), token.getExpiresIn(),
                TimeUnit.SECONDS);
        super.storeAccessToken(token, authentication);

    }

}
