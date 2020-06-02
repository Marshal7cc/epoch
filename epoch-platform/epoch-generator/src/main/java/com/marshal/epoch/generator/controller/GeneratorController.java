package com.marshal.epoch.generator.controller;


import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * epoch代码生成器
 * @author Marshal
 */
@RestController
public class GeneratorController {

    @Autowired
    private GeneratorService service;

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
