package com.marshal.epoch.hr.service.impl;


import com.marshal.epoch.common.dto.TreeNode;
import com.marshal.epoch.database.service.impl.BaseServiceImpl;
import com.marshal.epoch.common.util.TreeUtil;
import com.marshal.epoch.hr.mapper.HrDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.epoch.hr.entity.HrDepartment;
import com.marshal.epoch.hr.service.HrDepartmentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class HrDepartmentServiceImpl extends BaseServiceImpl<HrDepartment> implements HrDepartmentService {

    @Autowired
    private HrDepartmentMapper departmentMapper;

    @Override
    public List<TreeNode> queryDepartmentTree(HrDepartment dto) {
        List<TreeNode> list = departmentMapper.selectForTree(dto);
        return TreeUtil.build(list);
    }
}
