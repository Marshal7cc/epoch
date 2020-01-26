package com.marshal.epoch.system.controller;


import com.marshal.epoch.system.api.SysRoleApi;
import com.marshal.epoch.system.entity.SysRole;
import com.marshal.epoch.system.service.SysRoleService;
import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SysRoleController implements SysRoleApi {

    @Autowired
    SysRoleService sysRoleService;

    public ResponseEntity query(int page,
                                int pageSize,
                                SysRole condition) {
        List<SysRole> list = sysRoleService.select(condition, page, pageSize);
        return ResponseUtil.responseOk(list);
    }

    public ResponseEntity save(@RequestBody SysRole sysRole) {
        sysRoleService.submit(sysRole);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity delete(@RequestBody List<SysRole> list) {
        sysRoleService.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(sysRoleService.selectByPrimaryKey(id));
    }

}
