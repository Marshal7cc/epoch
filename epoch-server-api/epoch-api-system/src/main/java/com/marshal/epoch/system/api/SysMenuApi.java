package com.marshal.epoch.system.api;


import com.marshal.epoch.core.dto.ResponseEntity;

import com.marshal.epoch.system.entity.SysMenu;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/SysMenu")
public interface SysMenuApi{

    @RequestMapping(value = "/query")
    ResponseEntity query(SysMenu dto,
                         @RequestParam int page,
                         @RequestParam int pageSize);

    @RequestMapping(value = "/submit")
    ResponseEntity submit(@RequestBody SysMenu dto);

    @RequestMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<SysMenu> list);
}
