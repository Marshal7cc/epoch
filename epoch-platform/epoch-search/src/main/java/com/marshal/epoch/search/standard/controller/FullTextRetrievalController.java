package com.marshal.epoch.search.standard.controller;

import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.util.ResponseUtil;
import com.marshal.epoch.search.standard.dto.FullTextRetrievalDto;
import com.marshal.epoch.search.standard.dto.SysUser;
import com.marshal.epoch.search.standard.servcie.SysUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth: Marshal
 * @date: 2020/2/16
 * @desc:
 */
@RestController
public class FullTextRetrievalController {

    @Autowired
    private SysUserSearchService sysUserSearchService;

    @PostMapping("/query")
    public ResponseEntity query(@RequestBody FullTextRetrievalDto dto) {
        return ResponseUtil.responseOk(sysUserSearchService.query(dto));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody SysUser dto) {
        sysUserSearchService.save(dto);
        return ResponseUtil.responseOk();
    }

    @GetMapping("/createIndex")
    public ResponseEntity createIndex() {
        sysUserSearchService.createIndex(SysUser.class);
        return ResponseUtil.responseOk();
    }

    @GetMapping("/putMappings")
    public ResponseEntity putMappings() {
        sysUserSearchService.putMapping(SysUser.class);
        return ResponseUtil.responseOk();
    }

    @GetMapping("/deleteIndex")
    public ResponseEntity deleteIndex() {
        sysUserSearchService.deleteIndex(SysUser.class);
        return ResponseUtil.responseOk();
    }
}
