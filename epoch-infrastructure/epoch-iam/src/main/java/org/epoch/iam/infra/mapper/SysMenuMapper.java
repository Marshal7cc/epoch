package org.epoch.iam.infra.mapper;


import java.util.List;

import org.epoch.core.algorithm.tree.TreeNode;
import org.epoch.iam.domain.entity.SysMenu;
import tk.mybatis.mapper.common.Mapper;

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
