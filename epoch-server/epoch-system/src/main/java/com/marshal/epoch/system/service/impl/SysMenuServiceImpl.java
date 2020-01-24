package com.marshal.epoch.system.service.impl;


import com.marshal.epoch.core.dto.TreeNode;
import com.marshal.epoch.core.util.TreeUtil;
import com.marshal.epoch.system.dto.VueRouter;
import com.marshal.epoch.system.dto.VueRouterMeta;
import com.marshal.epoch.core.service.impl.BaseServiceImpl;
import com.marshal.epoch.system.mapper.SysMenuMapper;
import com.marshal.epoch.system.util.VueRouterTreeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.entity.SysMenu;
import com.marshal.epoch.system.service.SysMenuService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<TreeNode> queryMenuTree(SysMenu dto) {
        List<TreeNode> treeNodes = menuMapper.selectForTree(dto);
        return TreeUtil.build(treeNodes);
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
