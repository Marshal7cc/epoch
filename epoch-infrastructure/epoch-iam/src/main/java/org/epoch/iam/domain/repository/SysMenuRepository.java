package org.epoch.iam.domain.repository;


import org.epoch.core.algorithm.tree.TreeNode;
import org.epoch.iam.api.dto.VueRouter;
import org.epoch.mybatis.repository.BaseRepository;

import org.epoch.iam.domain.entity.SysMenu;

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
