package org.epoch.search.standard.controller;

import org.epoch.core.rest.ResponseEntity;
import org.epoch.core.rest.Response;
import org.epoch.search.standard.dto.FullTextRetrievalDto;
import org.epoch.search.standard.dto.SysUser;
import org.epoch.search.standard.servcie.SysUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 * @date 2020/2/16
 */
@RestController
public class FullTextRetrievalController {

    @Autowired
    private SysUserSearchService sysUserSearchService;

    @PostMapping("/query")
    public ResponseEntity query(@RequestBody FullTextRetrievalDto dto) {
        return Response.success(sysUserSearchService.query(dto));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody SysUser dto) {
        sysUserSearchService.save(dto);
        return Response.success();
    }

    @GetMapping("/createIndex")
    public ResponseEntity createIndex() {
        sysUserSearchService.createIndex(SysUser.class);
        return Response.success();
    }

    @GetMapping("/putMappings")
    public ResponseEntity putMappings() {
        sysUserSearchService.putMapping(SysUser.class);
        return Response.success();
    }

    @GetMapping("/deleteIndex")
    public ResponseEntity deleteIndex() {
        sysUserSearchService.deleteIndex(SysUser.class);
        return Response.success();
    }
}
