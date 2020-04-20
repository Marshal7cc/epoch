package com.marshal.epoch.hr.controller;


import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.util.ResponseUtil;

import com.marshal.epoch.hr.entity.HrCompany;
import com.marshal.epoch.hr.api.HrCompanyApi;
import com.marshal.epoch.hr.service.HrCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HrCompanyController implements HrCompanyApi {

    @Autowired
    private HrCompanyService service;

    public ResponseEntity query() {
        return ResponseUtil.responseOk(service.selectOne(null));
    }

    public ResponseEntity submit(HrCompany dto) {
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<HrCompany> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

}
