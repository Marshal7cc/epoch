package com.marshal.epoch.base.account.service.impl;

import com.marshal.epoch.base.account.dto.SysRole;
import com.marshal.epoch.base.account.service.SysRoleService;
import com.marshal.epoch.core.base.BaseServiceImpl;
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
