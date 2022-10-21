package org.epoch.iam.infrastructure.repository.impl;


import org.epoch.iam.domain.repository.SysResourceRepository;
import org.epoch.iam.infrastructure.repository.entity.SysResource;
import org.epoch.iam.infrastructure.repository.mapper.SysResourceMapper;
import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 */
@Service
public class SysResourceRepositoryImpl extends BaseMybatisRepository<SysResourceMapper, SysResource, Long> implements SysResourceRepository {

}
