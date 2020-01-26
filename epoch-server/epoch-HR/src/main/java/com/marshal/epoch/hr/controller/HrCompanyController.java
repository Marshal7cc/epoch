package com.marshal.epoch.hr.controller;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;

import com.marshal.epoch.hr.entity.HrCompany;
import com.marshal.epoch.hr.api.HrCompanyApi;
import com.marshal.epoch.hr.service.HrCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HrCompanyController implements HrCompanyApi {

    @Autowired
    private HrCompanyService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                HrCompany dto) {
        return ResponseUtil.responseOk(service.select(dto, page, pageSize));
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
