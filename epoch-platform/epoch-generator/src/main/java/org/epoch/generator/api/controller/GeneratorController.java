package org.epoch.generator.api.controller;


import org.epoch.generator.api.dto.GeneratorConfig;
import org.epoch.generator.app.service.GeneratorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * epoch代码生成器
 *
 * @author Marshal
 */
@Api(tags = "代码生成器服务")
@RequestMapping
@RestController("generatorController.v1")
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
                                HttpServletResponse response) {
        service.generatorFile(generatorConfig, response);
    }
}
