package org.epoch.iam.infra.repository.impl;


import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.epoch.core.algorithm.tree.TreeBuilder;
import org.epoch.core.algorithm.tree.TreeNode;
import org.epoch.iam.api.dto.VueRouter;
import org.epoch.iam.api.dto.VueRouterMeta;
import org.epoch.iam.domain.entity.SysMenu;
import org.epoch.iam.domain.repository.SysMenuRepository;
import org.epoch.iam.infra.mapper.SysMenuMapper;
import org.epoch.iam.infra.util.VueRouterTreeUtil;
import org.epoch.mybatis.repository.BaseMybatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuRepositoryImpl extends BaseMybatisRepository<SysMenuMapper, SysMenu, Long> implements SysMenuRepository {

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
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", menuId);
        List<SysMenu> childMenuList = menuMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(childMenuList)) {
            for (SysMenu item : childMenuList) {
                removeRecursion(item);
            }
        }
        menuMapper.deleteById(sysMenu);
    }

    @Override
    public List<VueRouter> getUserMenu() {
        List<SysMenu> list = menuMapper.selectList(Wrappers.emptyWrapper());
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
