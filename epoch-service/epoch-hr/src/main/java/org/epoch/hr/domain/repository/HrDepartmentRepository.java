package org.epoch.hr.domain.repository;


import org.epoch.core.algorithm.tree.TreeNode;
import org.epoch.mybatis.repository.BaseRepository;

import org.epoch.hr.domain.entity.HrDepartment;

import java.util.List;

/**
 * @author Marshal
 */
public interface HrDepartmentRepository extends BaseRepository<HrDepartment> {

    /**
     * 获取部门树
     *
     * @param dto
     * @return
     */
    List<TreeNode> queryDepartmentTree(HrDepartment dto);

}
