package com.marshal.epoch.system.api;


import com.marshal.epoch.common.dto.ResponseEntity;

import com.marshal.epoch.system.entity.SysResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SysResource")
public interface SysResourceApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody SysResource dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody SysResource dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<SysResource> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

}
