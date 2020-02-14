package com.marshal.epoch.hr.controller;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;

import com.marshal.epoch.hr.entity.HrEmployee;
import com.marshal.epoch.hr.api.HrEmployeeApi;
import com.marshal.epoch.hr.service.HrEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HrEmployeeController implements HrEmployeeApi {

    @Autowired
    private HrEmployeeService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                HrEmployee dto) {
        return ResponseUtil.responseOk(service.select(dto, page, pageSize));
    }

    public ResponseEntity submit(HrEmployee dto) {
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<HrEmployee> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

}
