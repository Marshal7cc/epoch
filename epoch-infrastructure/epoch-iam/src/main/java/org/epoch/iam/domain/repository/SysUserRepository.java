package org.epoch.iam.domain.repository;


import org.epoch.mybatis.repository.BaseRepository;

import org.epoch.iam.domain.entity.SysUser;

/**
 * @author Marshal
 */
public interface SysUserRepository extends BaseRepository<SysUser> {

    /**
     * 分布式事务测试方法
     */
    void txTest();

}
