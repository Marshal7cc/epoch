package com.marshal.epoch.hr.infra.repository.impl;


import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;

import com.marshal.epoch.hr.domain.entity.HrPosition;
import com.marshal.epoch.hr.domain.repository.HrPositionRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HrPositionRepositoryImpl extends BaseRepositoryImpl<HrPosition> implements HrPositionRepository {

}
