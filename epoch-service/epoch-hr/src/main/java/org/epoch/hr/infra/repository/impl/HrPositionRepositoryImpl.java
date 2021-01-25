package org.epoch.hr.infra.repository.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.epoch.hr.domain.entity.HrPosition;
import org.epoch.hr.domain.repository.HrPositionRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HrPositionRepositoryImpl extends BaseRepositoryImpl<HrPosition> implements HrPositionRepository {

}
