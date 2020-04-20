package com.marshal.epoch.system.service;


import com.marshal.epoch.common.dto.TreeNode;
import com.marshal.epoch.system.dto.VueRouter;
import com.marshal.epoch.database.service.BaseService;

import com.marshal.epoch.system.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 获取菜单树
     *
     * @param dto
     * @return
     */
    List<TreeNode> queryMenuTree(SysMenu dto);

    /**
     * 删除菜单，并递归删除所有子节点
     *
     * @param list
     */
    void remove(List<SysMenu> list);

    /**
     * 获取用户菜单
     *
     * @return
     */
    List<VueRouter> getUserMenu();

}
