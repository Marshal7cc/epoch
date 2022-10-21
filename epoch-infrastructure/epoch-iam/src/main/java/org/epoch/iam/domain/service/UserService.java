package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.domain.dto.UserDTO;
import org.epoch.iam.domain.repository.SysUserRepository;
import org.epoch.iam.infrastructure.repository.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class UserService extends BaseServiceImpl<SysUserRepository, UserDTO, SysUser, Long> {
}
