package org.epoch.iam.infra.repository.impl;


import org.epoch.iam.domain.entity.SysResource;
import org.epoch.iam.domain.repository.SysResourceRepository;
import org.epoch.starter.mybatis.repository.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysResourceRepositoryImpl extends BaseRepositoryImpl<SysResource> implements SysResourceRepository {

}
