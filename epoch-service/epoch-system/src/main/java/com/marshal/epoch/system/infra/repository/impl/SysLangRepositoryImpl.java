package com.marshal.epoch.system.infra.repository.impl;


import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.domain.entity.SysLang;
import com.marshal.epoch.system.domain.repository.SysLangRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLangRepositoryImpl extends BaseRepositoryImpl<SysLang> implements SysLangRepository {

}
