package com.marshal.epoch.system.controller;


import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.util.ResponseUtil;

import com.marshal.epoch.system.entity.SysPrompt;
import com.marshal.epoch.system.api.SysPromptApi;
import com.marshal.epoch.system.service.SysPromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SysPromptController implements SysPromptApi {

    @Autowired
    private SysPromptService service;

    public ResponseEntity query(int page,
                                int pageSize,
                                SysPrompt dto) {
        return ResponseUtil.responseOk(service.select(dto, page, pageSize));
    }

    public ResponseEntity submit(SysPrompt dto) {
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<SysPrompt> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

    @Override
    public ResponseEntity queryForI18n(String langCode) {
        return ResponseUtil.responseOk(service.queryForI18n(langCode));
    }
}
