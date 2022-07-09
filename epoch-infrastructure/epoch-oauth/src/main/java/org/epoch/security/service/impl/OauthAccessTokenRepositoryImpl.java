package org.epoch.security.service.impl;


import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.epoch.security.entity.OauthAccessToken;
import org.epoch.security.mapper.OauthAccessTokenMapper;
import org.epoch.security.service.OauthAccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @date 2019/3/6
 */
@Service
public class OauthAccessTokenRepositoryImpl extends BaseMybatisRepository<OauthAccessTokenMapper, OauthAccessToken, String> implements OauthAccessTokenRepository {

    @Autowired
    private OauthAccessTokenMapper oauthAccessTokenMapper;

}
