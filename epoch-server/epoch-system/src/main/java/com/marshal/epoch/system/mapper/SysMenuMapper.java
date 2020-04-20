package com.marshal.epoch.system.mapper;


import com.marshal.epoch.common.dto.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import com.marshal.epoch.system.entity.SysMenu;

import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {

    List<TreeNode> selectForTree(SysMenu dto);

}
