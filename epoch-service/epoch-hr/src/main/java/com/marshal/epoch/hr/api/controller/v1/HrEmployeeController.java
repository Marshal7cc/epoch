package com.marshal.epoch.hr.api.controller.v1;


import com.marshal.epoch.hr.domain.entity.HrEmployee;
import com.marshal.epoch.hr.api.HrEmployeeApi;
import com.marshal.epoch.hr.domain.repository.HrEmployeeRepository;
import com.marshal.epoch.mybatis.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RestController
@RequestMapping("/employees")
public class HrEmployeeController extends BaseController<HrEmployee, HrEmployeeRepository> implements HrEmployeeApi {

    @Autowired
    private HrEmployeeRepository service;


}
