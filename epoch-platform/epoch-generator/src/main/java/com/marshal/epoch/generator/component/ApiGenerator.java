package com.marshal.epoch.generator.component;

import com.marshal.epoch.generator.dto.DbTable;
import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.enums.FileType;
import com.marshal.epoch.generator.util.TemplateUtil;
import org.springframework.stereotype.Component;

/**
 * @author Marshal
 * @date 2020/1/20
 *
 */
@Component
public class ApiGenerator implements AbstractGenerator {

    @Override
    public FileType getFileType() {
        return FileType.Api;
    }

    @Override
    public byte[] generate(DbTable table, GeneratorConfig generatorConfig) throws Exception {
        return TemplateUtil.createFtlInfoByType(FileType.Api, table, generatorConfig);
    }

}
