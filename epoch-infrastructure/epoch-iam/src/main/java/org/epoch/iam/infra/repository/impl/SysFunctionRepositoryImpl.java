package org.epoch.iam.infra.repository.impl;


import org.epoch.iam.domain.entity.SysFunction;
import org.epoch.iam.domain.repository.SysFunctionRepository;
import org.epoch.iam.infra.mapper.SysFunctionMapper;
import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysFunctionRepositoryImpl extends BaseMybatisRepository<SysFunctionMapper, SysFunction, Long> implements SysFunctionRepository {

}
