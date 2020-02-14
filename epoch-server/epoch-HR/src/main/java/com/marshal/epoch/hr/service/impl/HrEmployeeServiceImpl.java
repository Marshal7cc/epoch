package com.marshal.epoch.hr.service.impl;


import com.marshal.epoch.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.hr.entity.HrEmployee;
import com.marshal.epoch.hr.service.HrEmployeeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HrEmployeeServiceImpl extends BaseServiceImpl<HrEmployee> implements HrEmployeeService {

}
