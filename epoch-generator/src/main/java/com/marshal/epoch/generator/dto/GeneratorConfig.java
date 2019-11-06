package com.marshal.epoch.generator.dto;

import lombok.Data;

/**
 * @auth: Marshal
 * @date: 2019/11/6
 * @desc: 代码生成请求信息
 */
@Data
public class GeneratorConfig {

    private String dataSourceUrl;

    private String userName;

    private String password;

    /**
     * 生成方式
     * 1.ZIP
     * 2.LOCAL
     */
    private String generateMethod;

    private String projectPath;

    private String parentPackagePath;

    private String packagePath;

    private String targetName;
    private String controllerStatus;
    private String controllerName;
    private String mapperStatus;
    private String mapperName;
    private String mapperXmlStatus;
    private String mapperXmlName;
    private String serviceStatus;
    private String serviceName;
    private String implStatus;
    private String implName;
    private String dtoStatus;
    private String dtoName;
    private String htmlStatus;
    private String htmlName;
    private String htmlModelName;
}
