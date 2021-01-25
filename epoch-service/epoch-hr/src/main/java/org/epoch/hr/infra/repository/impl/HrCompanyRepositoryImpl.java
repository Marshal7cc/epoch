package org.epoch.hr.infra.repository.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.epoch.hr.domain.entity.HrCompany;
import org.epoch.hr.domain.repository.HrCompanyRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HrCompanyRepositoryImpl extends BaseRepositoryImpl<HrCompany> implements HrCompanyRepository {

}
