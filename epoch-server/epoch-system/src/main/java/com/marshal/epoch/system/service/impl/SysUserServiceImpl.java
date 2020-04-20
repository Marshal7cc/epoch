package com.marshal.epoch.system.service.impl;


import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.database.service.impl.BaseServiceImpl;
import com.marshal.epoch.hr.entity.HrEmployee;
import com.marshal.epoch.system.feign.HrEmployeeClient;
import com.marshal.epoch.system.mapper.SysUserMapper;
//import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.entity.SysUser;
import com.marshal.epoch.system.service.SysUserService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private HrEmployeeClient employeeClient;

    @Override
//    @GlobalTransactional
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

        ResponseEntity responseEntity = employeeClient.submit(employee);
        if (!responseEntity.getSuccess()) {
            throw new RuntimeException();
        }

    }
}
