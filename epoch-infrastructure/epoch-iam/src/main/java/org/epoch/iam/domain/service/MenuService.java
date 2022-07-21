package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.api.dto.MenuDTO;
import org.epoch.iam.domain.entity.SysMenu;
import org.epoch.iam.domain.repository.SysMenuRepository;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class MenuService extends BaseServiceImpl<SysMenuRepository, MenuDTO, SysMenu, Long> {
}
