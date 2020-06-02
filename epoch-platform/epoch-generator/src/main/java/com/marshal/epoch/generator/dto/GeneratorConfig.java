package com.marshal.epoch.generator.dto;

import lombok.Data;

/**
 * @author Marshal
 * @date 2019/11/6
 *  代码生成请求信息
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
     * 是否去掉表名前缀
     */
    private boolean isCutPrefix;

    /**
     * 项目父报名
     */
    private String parentPackagePath;
    /**
     * 项目路径
     */
    private String packagePath;

    /**
     * 表名
     */
    private String tableName;
    /**
     * dtoName
     */
    private String targetName;

}
