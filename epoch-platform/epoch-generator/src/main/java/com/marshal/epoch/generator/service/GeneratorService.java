package com.marshal.epoch.generator.service;


import com.marshal.epoch.generator.dto.GeneratorConfig;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface GeneratorService {
    List<String> showTables();

    void generatorFile(GeneratorConfig info, HttpServletResponse response) throws Exception;

}
