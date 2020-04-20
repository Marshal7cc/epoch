package com.marshal.epoch.system.controller;


import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.util.ResponseUtil;

import com.marshal.epoch.system.entity.SysFunction;
import com.marshal.epoch.system.api.SysFunctionApi;
import com.marshal.epoch.system.service.SysFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SysFunctionController implements SysFunctionApi{

    @Autowired
    private SysFunctionService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                SysFunction dto) {
        return ResponseUtil.responseOk(service.select(dto,page,pageSize));
    }

    public ResponseEntity submit(SysFunction dto){
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<SysFunction> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

}
