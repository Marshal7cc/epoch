package org.epoch.security.service.impl;


import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.epoch.security.entity.OauthClientDetails;
import org.epoch.security.mapper.OauthClientDetailsMapper;
import org.epoch.security.service.OauthClientDetailsRepository;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @date 2019/3/8
 */
@Service
public class OauthClientDetailsRepositoryImpl extends BaseMybatisRepository<OauthClientDetailsMapper, OauthClientDetails, String> implements OauthClientDetailsRepository {
}
