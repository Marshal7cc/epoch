package com.marshal.epoch.hr.controller;


import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.util.ResponseUtil;

import com.marshal.epoch.hr.entity.HrDepartment;
import com.marshal.epoch.hr.api.HrDepartmentApi;
import com.marshal.epoch.hr.service.HrDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HrDepartmentController implements HrDepartmentApi {

    @Autowired
    private HrDepartmentService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                HrDepartment dto) {
        return ResponseUtil.responseOk(service.select(dto, page, pageSize));
    }

    public ResponseEntity submit(HrDepartment dto) {
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<HrDepartment> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

    @Override
    public ResponseEntity queryDepartmentTree(HrDepartment dto) {
        return ResponseUtil.responseOk(service.queryDepartmentTree(dto));
    }
}
