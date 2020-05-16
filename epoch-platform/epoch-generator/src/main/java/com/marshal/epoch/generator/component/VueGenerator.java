package com.marshal.epoch.generator.component;

import com.marshal.epoch.generator.dto.DBTable;
import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.enums.FileType;
import com.marshal.epoch.generator.util.TemplateUtil;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2020/1/20
 * @desc:
 */
@Component
public class VueGenerator implements AbstractGenerator {

    @Override
    public FileType getFileType() {
        return FileType.Vue;
    }

    @Override
    public byte[] generate(DBTable table, GeneratorConfig generatorConfig) throws Exception {
        return TemplateUtil.createFtlInfoByType(FileType.Vue, table, generatorConfig);
    }

}
