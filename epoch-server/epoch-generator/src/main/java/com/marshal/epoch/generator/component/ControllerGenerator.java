package com.marshal.epoch.generator.component;

import com.marshal.epoch.database.service.BaseService;
import com.marshal.epoch.database.service.impl.BaseServiceImpl;
import com.marshal.epoch.generator.dto.*;
import com.marshal.epoch.generator.enums.FileType;
import com.marshal.epoch.generator.util.TemplateUtil;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2020/1/20
 * @desc:
 */
@Component
public class ControllerGenerator implements AbstractGenerator {

    @Override
    public FileType getFileType() {
        return FileType.Controller;
    }

    @Override
    public byte[] generate(DBTable table, GeneratorConfig generatorConfig) throws Exception {
        return TemplateUtil.createFtlInfoByType(FileType.Controller, table, generatorConfig);
    }

}
