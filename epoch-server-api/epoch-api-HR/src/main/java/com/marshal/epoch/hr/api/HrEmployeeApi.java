package com.marshal.epoch.hr.api;


import com.marshal.epoch.core.dto.ResponseEntity;

import com.marshal.epoch.hr.entity.HrEmployee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public interface HrEmployeeApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody HrEmployee dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody HrEmployee dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<HrEmployee> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

}
