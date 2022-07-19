package org.epoch.iam.infra.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.epoch.core.util.TreeNode;
import org.epoch.iam.domain.entity.SysMenu;

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
