package com.marshal.epoch.system.controller;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;
import com.marshal.epoch.system.api.SysLangApi;
import com.marshal.epoch.system.entity.SysLang;
import com.marshal.epoch.system.service.SysLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SysLangController implements SysLangApi {

    @Autowired
    private SysLangService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                SysLang dto) {
        return ResponseUtil.responseOk(service.select(dto, page, pageSize));
    }

    public ResponseEntity submit(SysLang dto) {
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<SysLang> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

    @Override
    public ResponseEntity queryForOptions() {
        return ResponseUtil.responseOk(service.selectAll());
    }
}
