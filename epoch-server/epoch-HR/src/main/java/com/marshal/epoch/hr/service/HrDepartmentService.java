package com.marshal.epoch.hr.service;


import com.marshal.epoch.common.dto.TreeNode;
import com.marshal.epoch.database.service.BaseService;

import com.marshal.epoch.hr.entity.HrDepartment;

import java.util.List;

public interface HrDepartmentService extends BaseService<HrDepartment> {

    List<TreeNode> queryDepartmentTree(HrDepartment dto);

}
