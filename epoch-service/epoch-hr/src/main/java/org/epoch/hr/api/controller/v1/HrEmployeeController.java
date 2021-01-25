package org.epoch.hr.api.controller.v1;


import org.epoch.hr.domain.entity.HrEmployee;
import org.epoch.hr.api.HrEmployeeApi;
import org.epoch.hr.domain.repository.HrEmployeeRepository;
import org.epoch.mybatis.common.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RestController
@RequestMapping("/employees")
public class HrEmployeeController extends CommonController<HrEmployee, HrEmployeeRepository> implements HrEmployeeApi {

    @Autowired
    private HrEmployeeRepository service;


}
