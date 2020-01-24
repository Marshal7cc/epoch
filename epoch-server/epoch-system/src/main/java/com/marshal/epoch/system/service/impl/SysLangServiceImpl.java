package com.marshal.epoch.system.service.impl;


import com.marshal.epoch.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.entity.SysLang;
import com.marshal.epoch.system.service.SysLangService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysLangServiceImpl extends BaseServiceImpl<SysLang> implements SysLangService {

}
