package com.marshal.epoch.system.infra.repository.impl;


import com.marshal.epoch.core.algorithm.tree.TreeNode;
import com.marshal.epoch.core.algorithm.tree.TreeBuilder;
import com.marshal.epoch.system.api.dto.VueRouter;
import com.marshal.epoch.system.api.dto.VueRouterMeta;
import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import com.marshal.epoch.system.infra.mapper.SysMenuMapper;
import com.marshal.epoch.system.infra.util.VueRouterTreeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.domain.entity.SysMenu;
import com.marshal.epoch.system.domain.repository.SysMenuRepository;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuRepositoryImpl extends BaseRepositoryImpl<SysMenu> implements SysMenuRepository {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<TreeNode> queryMenuTree(SysMenu dto) {
        List<TreeNode> treeNodes = menuMapper.selectForTree(dto);
        return TreeBuilder.build(treeNodes);
    }

    @Override
    public void remove(List<SysMenu> list) {
        for (SysMenu item : list) {
            removeRecursion(item);
        }
    }

    /**
     * 递归删除
     *
     * @param sysMenu
     */
    private void removeRecursion(SysMenu sysMenu) {
        Long menuId = sysMenu.getMenuId();
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("parent_id", menuId);
        List<SysMenu> childMenuList = menuMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(childMenuList)) {
            for (SysMenu item : childMenuList) {
                removeRecursion(item);
            }
        }
        menuMapper.deleteByPrimaryKey(sysMenu);
    }

    @Override
    public List<VueRouter> getUserMenu() {
        List<SysMenu> list = menuMapper.selectAll();
        List<VueRouter> routers = new ArrayList<>();
        list.forEach(item -> {
            VueRouter route = new VueRouter();
            route.setId(item.getMenuId());
            route.setParentId(item.getParentId());
            route.setPath(item.getPath());
            route.setComponent(item.getComponent());
            route.setName(item.getMenuName());
            route.setMeta(new VueRouterMeta(item.getMenuName(), item.getMenuIcon(), true));
            routers.add(route);
        });
        return VueRouterTreeUtil.buildVueRouter(routers);
    }
}
