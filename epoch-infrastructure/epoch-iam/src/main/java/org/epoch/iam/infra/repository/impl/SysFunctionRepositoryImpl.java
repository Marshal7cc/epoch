package org.epoch.iam.infra.repository.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;

import org.epoch.iam.domain.entity.SysFunction;
import org.epoch.iam.domain.repository.SysFunctionRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysFunctionRepositoryImpl extends BaseRepositoryImpl<SysFunction> implements SysFunctionRepository {

}
