package com.marshal.epoch.hr.controller;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;

import com.marshal.epoch.hr.entity.HrPosition;
import com.marshal.epoch.hr.api.HrPositionApi;
import com.marshal.epoch.hr.service.HrPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HrPositionController implements HrPositionApi {

    @Autowired
    private HrPositionService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                HrPosition dto) {
        return ResponseUtil.responseOk(service.select(dto, page, pageSize));
    }

    public ResponseEntity submit(HrPosition dto) {
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<HrPosition> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

}
