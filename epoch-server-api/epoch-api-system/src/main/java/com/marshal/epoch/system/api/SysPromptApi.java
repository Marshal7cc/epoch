package com.marshal.epoch.system.api;


import com.marshal.epoch.common.dto.ResponseEntity;

import com.marshal.epoch.system.entity.SysPrompt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prompt")
public interface SysPromptApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody SysPrompt dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody SysPrompt dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<SysPrompt> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

    @GetMapping(value = "/i18n")
    ResponseEntity queryForI18n(@RequestParam String langCode);

}
