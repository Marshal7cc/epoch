package org.epoch.hr.infra.repository.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.epoch.hr.domain.entity.HrEmployee;
import org.epoch.hr.domain.repository.HrEmployeeRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HrEmployeeRepositoryImpl extends BaseRepositoryImpl<HrEmployee> implements HrEmployeeRepository {

}
