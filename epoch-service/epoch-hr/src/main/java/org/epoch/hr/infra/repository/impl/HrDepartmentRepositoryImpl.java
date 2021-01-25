package org.epoch.hr.infra.repository.impl;


import org.epoch.core.algorithm.tree.TreeNode;
import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.epoch.core.algorithm.tree.TreeBuilder;
import org.epoch.hr.infra.mapper.HrDepartmentMapper;
import org.epoch.hr.domain.entity.HrDepartment;
import org.epoch.hr.domain.repository.HrDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HrDepartmentRepositoryImpl extends BaseRepositoryImpl<HrDepartment> implements HrDepartmentRepository {

    @Autowired
    private HrDepartmentMapper departmentMapper;

    @Override
    public List<TreeNode> queryDepartmentTree(HrDepartment dto) {
        List<TreeNode> list = departmentMapper.selectForTree(dto);
        return TreeBuilder.build(list);
    }
}
