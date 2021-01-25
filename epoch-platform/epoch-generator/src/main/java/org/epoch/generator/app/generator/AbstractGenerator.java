package org.epoch.generator.app.generator;

import org.epoch.generator.api.dto.DbTable;
import org.epoch.generator.api.dto.GeneratorConfig;
import org.epoch.generator.infra.enums.FileType;

/**
 * 代码生成器抽象接口
 *
 * @author Marshal
 */
public interface AbstractGenerator {

    /**
     * 生成文件类型,以文件类型区分generator
     *
     * @return 文件类型
     */
    FileType getFileType();


    /**
     * 根据配置信息表信息，生成字节数组，用于打压缩包
     *
     * @param table 数据库表信息
     * @param info  代码生成参数
     * @return 字节数组
     * @throws Exception
     */
    byte[] generate(DbTable table, GeneratorConfig info) throws Exception;

}
