package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.domain.dto.MenuDTO;
import org.epoch.iam.domain.repository.SysMenuRepository;
import org.epoch.iam.infrastructure.repository.entity.SysMenu;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class MenuService extends BaseServiceImpl<SysMenuRepository, MenuDTO, SysMenu, Long> {
}
