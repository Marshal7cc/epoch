package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.domain.dto.RoleDTO;
import org.epoch.iam.domain.repository.SysRoleRepository;
import org.epoch.iam.infrastructure.repository.entity.SysRole;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class RoleService extends BaseServiceImpl<SysRoleRepository, RoleDTO, SysRole, Long> {
}
