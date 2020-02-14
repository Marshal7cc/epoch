package com.marshal.epoch.hr.api;


import com.marshal.epoch.core.dto.ResponseEntity;

import com.marshal.epoch.hr.entity.HrPosition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public interface HrPositionApi {

    @PostMapping(value = "/query")
    ResponseEntity query(@RequestParam int page,
                         @RequestParam int pageSize,
                         @RequestBody HrPosition dto);

    @PostMapping(value = "/submit")
    ResponseEntity submit(@RequestBody HrPosition dto);

    @PostMapping(value = "/remove")
    ResponseEntity remove(@RequestBody List<HrPosition> list);

    @GetMapping(value = "/queryById")
    ResponseEntity queryById(@RequestParam Long id);

}
