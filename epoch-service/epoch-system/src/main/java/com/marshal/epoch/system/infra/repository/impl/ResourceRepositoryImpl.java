package com.marshal.epoch.system.infra.repository.impl;


import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import com.marshal.epoch.system.domain.entity.Resource;
import com.marshal.epoch.system.domain.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceRepositoryImpl extends BaseRepositoryImpl<Resource> implements ResourceRepository{

}
