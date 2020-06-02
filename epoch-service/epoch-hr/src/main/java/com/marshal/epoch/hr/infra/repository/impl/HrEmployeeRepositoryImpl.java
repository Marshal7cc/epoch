package com.marshal.epoch.hr.infra.repository.impl;


import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.hr.domain.entity.HrEmployee;
import com.marshal.epoch.hr.domain.repository.HrEmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HrEmployeeRepositoryImpl extends BaseRepositoryImpl<HrEmployee> implements HrEmployeeRepository {

}
