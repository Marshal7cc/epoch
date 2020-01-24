package com.marshal.epoch.system.api;


import com.marshal.epoch.core.dto.ResponseEntity;

import com.marshal.epoch.system.entity.SysLang;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lang")
public interface SysLangApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody SysLang dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody SysLang dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<SysLang> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

    @GetMapping(value = "/queryForOptions")
    ResponseEntity queryForOptions();
}
