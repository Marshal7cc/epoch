package org.epoch.iam.infra.repository.impl;


import org.epoch.iam.domain.entity.SysLang;
import org.epoch.iam.domain.repository.SysLangRepository;
import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLangRepositoryImpl extends BaseRepositoryImpl<SysLang> implements SysLangRepository {

}
