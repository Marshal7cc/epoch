package com.marshal.epoch.system.service.impl;


import com.marshal.epoch.database.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.entity.SysResource;
import com.marshal.epoch.system.service.SysResourceService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

}
