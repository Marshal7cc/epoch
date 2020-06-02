package com.marshal.epoch.system.domain.repository;


import com.marshal.epoch.core.algorithm.tree.TreeNode;
import com.marshal.epoch.system.api.dto.VueRouter;
import com.marshal.epoch.mybatis.service.BaseRepository;

import com.marshal.epoch.system.domain.entity.SysMenu;

import java.util.List;

/**
 * @author Marshal
 */
public interface SysMenuRepository extends BaseRepository<SysMenu> {

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
