package com.marshal.epoch.security.service.impl;


import com.marshal.epoch.security.entity.OauthAccessToken;
import com.marshal.epoch.security.mapper.OauthAccessTokenMapper;
import com.marshal.epoch.security.service.OauthAccessTokenRepository;
import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
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
