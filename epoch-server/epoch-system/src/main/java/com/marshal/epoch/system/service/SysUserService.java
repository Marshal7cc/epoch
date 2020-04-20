package com.marshal.epoch.system.service;


import com.marshal.epoch.database.service.BaseService;

import com.marshal.epoch.system.entity.SysUser;

public interface SysUserService extends BaseService<SysUser> {

    /**
     * 分布式事务测试方法
     */
    void txTest();

}
