package org.epoch.iam.infra.repository.impl;


import org.epoch.iam.domain.entity.Resource;
import org.epoch.iam.domain.repository.ResourceRepository;
import org.epoch.starter.mybatis.repository.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceRepositoryImpl extends BaseRepositoryImpl<Resource> implements ResourceRepository{

}
