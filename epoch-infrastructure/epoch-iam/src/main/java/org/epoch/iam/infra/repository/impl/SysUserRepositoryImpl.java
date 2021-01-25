package org.epoch.iam.infra.repository.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;

import org.epoch.iam.infra.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.epoch.iam.domain.entity.SysUser;
import org.epoch.iam.domain.repository.SysUserRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRepositoryImpl extends BaseRepositoryImpl<SysUser> implements SysUserRepository {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * seata测试
     */
    @Override
    public void txTest() {
        SysUser sysUser = userMapper.selectByPrimaryKey(10001);
        sysUser.setUserId(10099L);
        sysUser.setUserName("老张");
        userMapper.insert(sysUser);


    }
}
