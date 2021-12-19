package org.epoch.security.service.impl;


import org.epoch.security.entity.OauthAccessToken;
import org.epoch.security.mapper.OauthAccessTokenMapper;
import org.epoch.security.service.OauthAccessTokenRepository;
import org.epoch.starter.mybatis.repository.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @date 2019/3/6
 *
 */
@Service
public class OauthAccessTokenRepositoryImpl extends BaseRepositoryImpl<OauthAccessToken> implements OauthAccessTokenRepository {

    @Autowired
    private OauthAccessTokenMapper oauthAccessTokenMapper;

}
