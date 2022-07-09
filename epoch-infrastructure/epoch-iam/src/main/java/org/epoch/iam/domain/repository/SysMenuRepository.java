package org.epoch.iam.domain.repository;


import java.util.List;

import org.epoch.core.algorithm.tree.TreeNode;
import org.epoch.iam.api.dto.VueRouter;
import org.epoch.iam.domain.entity.SysMenu;
import org.epoch.data.repository.BaseRepository;

/**
 * @author Marshal
 */
public interface SysMenuRepository extends BaseRepository<SysMenu,Long> {

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
