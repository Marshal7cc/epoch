package com.marshal.epoch.hr.service.impl;


import com.marshal.epoch.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.hr.entity.HrPosition;
import com.marshal.epoch.hr.service.HrPositionService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HrPositionServiceImpl extends BaseServiceImpl<HrPosition> implements HrPositionService {

}
