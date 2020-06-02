package com.marshal.epoch.generator.service;


import com.marshal.epoch.generator.dto.GeneratorConfig;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author Marshal
 */
public interface GeneratorService {
    /**
     * 获取所有表信息
     *
     * @return
     */
    List<String> showTables();

    /**
     * 生成文件
     *
     * @param info
     * @param response
     * @throws Exception
     */
    void generatorFile(GeneratorConfig info, HttpServletResponse response) throws Exception;

}
