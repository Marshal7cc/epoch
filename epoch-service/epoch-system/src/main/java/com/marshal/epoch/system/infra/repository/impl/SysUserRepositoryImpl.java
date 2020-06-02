package com.marshal.epoch.system.infra.repository.impl;


import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import com.marshal.epoch.hr.domain.entity.HrEmployee;
import com.marshal.epoch.system.infra.feign.HrEmployeeClient;
import com.marshal.epoch.system.infra.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.domain.entity.SysUser;
import com.marshal.epoch.system.domain.repository.SysUserRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRepositoryImpl extends BaseRepositoryImpl<SysUser> implements SysUserRepository {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private HrEmployeeClient employeeClient;

    /**
     * seata测试
     */
    @Override
    public void txTest() {
        SysUser sysUser = userMapper.selectByPrimaryKey(10001);
        sysUser.setUserId(10099L);
        sysUser.setUserName("老张");
        userMapper.insert(sysUser);

        HrEmployee employee = new HrEmployee();
        employee.setEmployeeId(10099L);
        employee.setEmployeeCode("MARSHAL20099");
        employee.setName("老张");
        employee.setObjectVersion(0L);

        ResponseEntity responseEntity1 = employeeClient.create(employee);
        ResponseEntity responseEntity = employeeClient.detail(10001L);
        if (!responseEntity.getSuccess()) {
            throw new RuntimeException();
        }

    }
}
