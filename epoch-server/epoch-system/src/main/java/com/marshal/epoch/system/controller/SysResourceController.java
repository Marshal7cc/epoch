package com.marshal.epoch.system.controller;


import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.util.ResponseUtil;

import com.marshal.epoch.system.entity.SysResource;
import com.marshal.epoch.system.api.SysResourceApi;
import com.marshal.epoch.system.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SysResourceController implements SysResourceApi {

    @Autowired
    private SysResourceService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                SysResource dto) {
        return ResponseUtil.responseOk(service.select(dto, page, pageSize));
    }

    public ResponseEntity submit(SysResource dto) {
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<SysResource> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

}
