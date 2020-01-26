package com.marshal.epoch.system.service.impl;


import com.marshal.epoch.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.entity.SysFunction;
import com.marshal.epoch.system.service.SysFunctionService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysFunctionServiceImpl extends BaseServiceImpl<SysFunction> implements SysFunctionService{

}