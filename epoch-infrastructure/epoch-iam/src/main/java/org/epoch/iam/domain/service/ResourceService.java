package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.api.dto.ResourceDTO;
import org.epoch.iam.domain.entity.SysResource;
import org.epoch.iam.domain.repository.SysResourceRepository;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class ResourceService extends BaseServiceImpl<SysResourceRepository, ResourceDTO, SysResource, Long> {
}
