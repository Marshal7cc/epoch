package com.marshal.epoch.security.service.impl;


import com.marshal.epoch.security.entity.OauthAccessToken;
import com.marshal.epoch.security.mapper.OauthAccessTokenMapper;
import com.marshal.epoch.security.service.OauthAccessTokenService;
import com.marshal.epoch.database.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auth: Marshal
 * @date: 2019/3/6
 * @desc:
 */
@Service
public class OauthAccessTokenServiceImpl extends BaseServiceImpl<OauthAccessToken> implements OauthAccessTokenService {

    @Autowired
    private OauthAccessTokenMapper oauthAccessTokenMapper;

}
