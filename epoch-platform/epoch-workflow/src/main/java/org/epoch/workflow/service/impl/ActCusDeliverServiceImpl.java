package org.epoch.workflow.service.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.epoch.workflow.entity.ActCusDeliver;
import org.epoch.workflow.service.ActCusDeliverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActCusDeliverServiceImpl extends BaseRepositoryImpl<ActCusDeliver> implements ActCusDeliverService {

}
