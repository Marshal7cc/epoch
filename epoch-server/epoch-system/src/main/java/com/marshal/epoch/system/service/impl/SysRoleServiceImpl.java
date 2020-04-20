package com.marshal.epoch.system.service.impl;


import com.marshal.epoch.system.entity.SysRole;
import com.marshal.epoch.system.service.SysRoleService;
import com.marshal.epoch.database.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auth: Marshal
 * @date: 2019/3/31
 * @desc:
 */
@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
}
