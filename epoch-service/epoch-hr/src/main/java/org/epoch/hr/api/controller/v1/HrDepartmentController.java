package org.epoch.hr.api.controller.v1;


import org.epoch.hr.domain.entity.HrDepartment;
import org.epoch.hr.api.HrDepartmentApi;
import org.epoch.hr.domain.repository.HrDepartmentRepository;
import org.epoch.mybatis.common.CommonController;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RequestMapping("/departments")
@RestController("departmentController.v1")
public class HrDepartmentController extends CommonController<HrDepartment, HrDepartmentRepository> implements HrDepartmentApi {
//    @Override
//    public ResponseEntity queryDepartmentTree(HrDepartment dto) {
//        return Response.success(service.queryDepartmentTree(dto));
//    }
}
