package org.epoch.iam.infrastructure.repository.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.epoch.core.util.TreeNode;
import org.epoch.iam.infrastructure.repository.entity.SysMenu;

/**
 * @author Marshal
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取树状菜单结构
     *
     * @param dto
     * @return
     */
    List<TreeNode> selectForTree(SysMenu dto);

}
