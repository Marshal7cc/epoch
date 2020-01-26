package com.marshal.epoch.hr.api;


import com.marshal.epoch.core.dto.ResponseEntity;

import com.marshal.epoch.hr.entity.HrCompany;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public interface HrCompanyApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody HrCompany dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody HrCompany dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<HrCompany> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

}
