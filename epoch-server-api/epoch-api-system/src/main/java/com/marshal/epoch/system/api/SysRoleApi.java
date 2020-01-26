package com.marshal.epoch.system.api;


import com.marshal.epoch.system.entity.SysRole;
import com.marshal.epoch.core.dto.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
public interface SysRoleApi {

    @PostMapping("/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody SysRole condition);

    @PostMapping("/submit")
    ResponseEntity save(@RequestBody SysRole sysRole);

    @PostMapping("/remove")
    ResponseEntity delete(@RequestBody List<SysRole> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);
}
