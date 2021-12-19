package org.epoch.security.service.impl;


import org.epoch.security.entity.OauthClientDetails;
import org.epoch.security.service.OauthClientDetailsRepository;
import org.epoch.starter.mybatis.repository.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @date 2019/3/8
 */
@Service
public class OauthClientDetailsRepositoryImpl extends BaseRepositoryImpl<OauthClientDetails> implements OauthClientDetailsRepository {
}
