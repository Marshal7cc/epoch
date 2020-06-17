package com.marshal.epoch.workflow.service.impl;


import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import com.marshal.epoch.workflow.entity.ActCusDeliver;
import com.marshal.epoch.workflow.service.ActCusDeliverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActCusDeliverServiceImpl extends BaseRepositoryImpl<ActCusDeliver> implements ActCusDeliverService {

}
