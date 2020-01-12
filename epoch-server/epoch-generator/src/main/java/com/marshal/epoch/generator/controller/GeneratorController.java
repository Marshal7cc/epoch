package com.marshal.epoch.generator.controller;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;
import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * epoch代码生成器
 */
@RestController
@RequestMapping(value = "/generator")
public class GeneratorController {

    @Autowired
    GeneratorService service;

    /**
     * 获取数据库中的所有表
     *
     * @return
     */
    @GetMapping(value = "/getAllTables")
    public ResponseEntity showTables() {
        List<String> list = service.showTables();
        List<Map<String, String>> resultMap = list.stream().map(item -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", item);
            map.put("text", item);
            return map;
        }).collect(Collectors.toList());
        return ResponseUtil.responseOk(resultMap);
    }

    /**
     * 生成文件
     *
     * @param generatorConfig
     * @return
     */
    @PostMapping(value = "/create")
    public void generatorTables(@RequestBody GeneratorConfig generatorConfig,
                                HttpServletResponse response) throws Exception {
        service.generatorFile(generatorConfig, response);
    }

}
