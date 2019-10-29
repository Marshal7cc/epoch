package com.marshal.epoch.base.account.controller;

import com.marshal.epoch.base.account.dto.SysRole;

import com.marshal.epoch.base.account.service.SysRoleService;

import com.marshal.epoch.core.base.BaseController;
import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {

    @Autowired
    SysRoleService sysRoleService;

    @PostMapping("/query/{page}/{pageSize}")
    public ResponseEntity query(@PathVariable("page") int page,
                                @PathVariable("pageSize") int pageSize,
                                @RequestBody SysRole condition) {
        List<SysRole> list = sysRoleService.select(condition, page, pageSize);
        return ResponseUtil.responseOk(list);
    }

    @RequestMapping("/submit")
    public ResponseEntity save(@RequestBody SysRole sysRole) {

        return ResponseUtil.responseOk();
    }

    @RequestMapping("/remove")
    public ResponseEntity delete(@RequestParam("selectedIds") Long[] selectedIds) {
        sysRoleService.batchDelete(selectedIds);
        return ResponseUtil.responseOk();
    }

}
