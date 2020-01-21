package com.marshal.epoch.generator.component;

import com.marshal.epoch.generator.dto.DBTable;
import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.enums.FileType;

/**
 * 代码生成器抽象接口
 */
public interface AbstractGenerator {

    /**
     * 文件类型
     *
     * @return
     */
    FileType getFileType();


    /**
     * 根据配置信息表信息，生成字节数组
     *
     * @param table
     * @param info
     * @return
     */
    byte[] generate(DBTable table, GeneratorConfig info) throws Exception;

}
