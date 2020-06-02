package com.marshal.epoch.hr.api.controller.v1;


import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.rest.Response;

import com.marshal.epoch.hr.domain.entity.HrDepartment;
import com.marshal.epoch.hr.api.HrDepartmentApi;
import com.marshal.epoch.hr.domain.repository.HrDepartmentRepository;
import com.marshal.epoch.mybatis.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marshal
 */
@RequestMapping("/departments")
@RestController("departmentController.v1")
public class HrDepartmentController extends BaseController<HrDepartment, HrDepartmentRepository> implements HrDepartmentApi {
//    @Override
//    public ResponseEntity queryDepartmentTree(HrDepartment dto) {
//        return Response.success(service.queryDepartmentTree(dto));
//    }
}
