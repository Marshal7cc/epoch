package org.epoch.iam.infrastructure.repository.impl;


import org.epoch.iam.domain.repository.SysRoleRepository;
import org.epoch.iam.infrastructure.repository.entity.SysRole;
import org.epoch.iam.infrastructure.repository.mapper.SysRoleMapper;
import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 * @date 2019/3/31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleRepositoryImpl extends BaseMybatisRepository<SysRoleMapper,SysRole,Long> implements SysRoleRepository {
}
