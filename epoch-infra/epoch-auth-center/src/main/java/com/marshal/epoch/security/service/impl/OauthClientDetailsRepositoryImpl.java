package com.marshal.epoch.security.service.impl;


import com.marshal.epoch.security.entity.OauthClientDetails;
import com.marshal.epoch.security.service.OauthClientDetailsRepository;
import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @date 2019/3/8
 */
@Service
public class OauthClientDetailsRepositoryImpl extends BaseRepositoryImpl<OauthClientDetails> implements OauthClientDetailsRepository {
}
