package com.marshal.epoch.hr.service.impl;


import com.marshal.epoch.database.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.hr.entity.HrCompany;
import com.marshal.epoch.hr.service.HrCompanyService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HrCompanyServiceImpl extends BaseServiceImpl<HrCompany> implements HrCompanyService{

}
