package com.marshal.epoch.system.infra.mapper;


import com.marshal.epoch.core.algorithm.tree.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import com.marshal.epoch.system.domain.entity.SysMenu;

import java.util.List;

/**
 * @author Marshal
 */
public interface SysMenuMapper extends Mapper<SysMenu> {

    /**
     * 获取树状菜单结构
     *
     * @param dto
     * @return
     */
    List<TreeNode> selectForTree(SysMenu dto);

}
