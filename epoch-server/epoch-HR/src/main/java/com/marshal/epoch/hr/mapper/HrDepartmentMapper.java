package com.marshal.epoch.hr.mapper;


import com.marshal.epoch.core.dto.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import com.marshal.epoch.hr.entity.HrDepartment;

import java.util.List;

public interface HrDepartmentMapper extends Mapper<HrDepartment> {

    List<TreeNode> selectForTree(HrDepartment dto);

}
