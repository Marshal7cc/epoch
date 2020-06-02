package com.marshal.epoch.system.infra.repository.impl;


import com.marshal.epoch.system.domain.entity.SysRole;
import com.marshal.epoch.system.domain.repository.SysRoleRepository;
import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
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
