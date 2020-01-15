package com.marshal.epoch.auth.service.impl;


import com.marshal.epoch.auth.entity.OauthAccessToken;
import com.marshal.epoch.auth.mapper.OauthAccessTokenMapper;
import com.marshal.epoch.auth.service.OauthAccessTokenService;
import com.marshal.epoch.core.service.impl.BaseServiceImpl;
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
