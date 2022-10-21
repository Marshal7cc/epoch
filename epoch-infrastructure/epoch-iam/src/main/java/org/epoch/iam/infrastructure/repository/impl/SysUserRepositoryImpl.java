package org.epoch.iam.infrastructure.repository.impl;


import org.epoch.iam.domain.repository.SysUserRepository;
import org.epoch.iam.infrastructure.repository.entity.SysUser;
import org.epoch.iam.infrastructure.repository.mapper.SysUserMapper;
import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRepositoryImpl extends BaseMybatisRepository<SysUserMapper, SysUser, Long> implements SysUserRepository {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * seata测试
     */
    @Override
    public void txTest() {
        SysUser sysUser = userMapper.selectById(10001);
        sysUser.setUserId(10099L);
        sysUser.setUserName("老张");
        userMapper.insert(sysUser);
    }
}
