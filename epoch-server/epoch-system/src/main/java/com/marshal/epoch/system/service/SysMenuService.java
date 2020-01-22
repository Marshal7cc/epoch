package com.marshal.epoch.system.service;


import com.marshal.epoch.system.dto.VueRouter;
import com.marshal.epoch.core.service.BaseService;

import com.marshal.epoch.system.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu> {

    List<VueRouter> getUserMenu();

}
