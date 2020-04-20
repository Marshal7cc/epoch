package com.marshal.epoch.hr.api;


import com.marshal.epoch.common.dto.ResponseEntity;

import com.marshal.epoch.hr.entity.HrDepartment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public interface HrDepartmentApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody HrDepartment dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody HrDepartment dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<HrDepartment> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

    @PostMapping(value = "/queryDepartmentTree")
    ResponseEntity queryDepartmentTree(@RequestBody HrDepartment dto);

}
