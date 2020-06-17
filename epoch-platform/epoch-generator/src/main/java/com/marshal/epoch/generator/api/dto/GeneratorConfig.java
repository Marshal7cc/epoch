package com.marshal.epoch.generator.api.dto;

import lombok.Data;

/**
 * 代码生成请求信息
 *
 * @author Marshal
 * @date 2019/11/6
 */
@Data
public class GeneratorConfig {
    /**
     * 数据源信息
     */
    private String dataSourceUrl;
    private String userName;
    private String password;

    /**
     * 项目路径
     * 如：com.marshal.epoch
     */
    private String packagePath;

    /**
     * 项目父包名
     * 如：system
     */
    private String parentPackagePath;
    /**
     * 表名
     */
    private String tableName;
    /**
     * entityName
     */
    private String entityName;
    /**
     * 作者名称
     */
    private String authorName;

}
