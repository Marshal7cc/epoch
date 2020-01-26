package com.marshal.epoch.system.api;


import com.marshal.epoch.core.dto.ResponseEntity;

import com.marshal.epoch.system.entity.SysFunction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/function")
public interface SysFunctionApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody SysFunction dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody SysFunction dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<SysFunction> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

}
