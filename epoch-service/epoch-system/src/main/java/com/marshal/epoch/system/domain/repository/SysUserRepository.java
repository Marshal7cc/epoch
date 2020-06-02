package com.marshal.epoch.system.domain.repository;


import com.marshal.epoch.mybatis.service.BaseRepository;

import com.marshal.epoch.system.domain.entity.SysUser;

/**
 * @author Marshal
 */
public interface SysUserRepository extends BaseRepository<SysUser> {

    /**
     * 分布式事务测试方法
     */
    void txTest();

}
