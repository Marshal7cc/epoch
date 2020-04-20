package com.marshal.epoch.system.api;


import com.marshal.epoch.common.dto.ResponseEntity;

import com.marshal.epoch.system.entity.SysMenu;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public interface SysMenuApi {

    @PostMapping(value = "/query")
    ResponseEntity query(SysMenu dto,
                         @RequestParam int page,
                         @RequestParam int pageSize);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody SysMenu dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<SysMenu> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

    @PostMapping(value = "/queryMenuTree")
    ResponseEntity queryMenuTree(@RequestBody SysMenu dto);

    @GetMapping(value = "/getUserMenu")
    ResponseEntity getUserMenu();
}
