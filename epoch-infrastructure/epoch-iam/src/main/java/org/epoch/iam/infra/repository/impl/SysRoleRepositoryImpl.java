package org.epoch.iam.infra.repository.impl;


import org.epoch.iam.domain.entity.SysRole;
import org.epoch.iam.domain.repository.SysRoleRepository;
import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 * @date 2019/3/31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleRepositoryImpl extends BaseRepositoryImpl<SysRole> implements SysRoleRepository {
}
