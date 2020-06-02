package com.marshal.epoch.hr.infra.repository.impl;


import com.marshal.epoch.core.algorithm.tree.TreeNode;
import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import com.marshal.epoch.core.algorithm.tree.TreeBuilder;
import com.marshal.epoch.hr.infra.mapper.HrDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.epoch.hr.domain.entity.HrDepartment;
import com.marshal.epoch.hr.domain.repository.HrDepartmentRepository;
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
