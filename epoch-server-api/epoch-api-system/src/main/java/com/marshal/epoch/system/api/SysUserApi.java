package com.marshal.epoch.system.api;


import com.marshal.epoch.common.dto.ResponseEntity;

import com.marshal.epoch.system.entity.SysUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public interface SysUserApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody SysUser dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody SysUser dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<SysUser> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

}
