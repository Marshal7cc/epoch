package com.marshal.epoch.hr.domain.repository;


import com.marshal.epoch.core.algorithm.tree.TreeNode;
import com.marshal.epoch.mybatis.service.BaseRepository;

import com.marshal.epoch.hr.domain.entity.HrDepartment;

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
