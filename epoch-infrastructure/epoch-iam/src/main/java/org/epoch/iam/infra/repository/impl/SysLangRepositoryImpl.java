package org.epoch.iam.infra.repository.impl;


import org.epoch.iam.domain.entity.SysLang;
import org.epoch.iam.domain.repository.SysLangRepository;
import org.epoch.iam.infra.mapper.SysLangMapper;
import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLangRepositoryImpl extends BaseMybatisRepository<SysLangMapper, SysLang, Long> implements SysLangRepository {

}
