package com.marshal.epoch.generator.dto;


import lombok.Data;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/5/18
 * @desc: 模板文件信息
 */
@Data
public class FtlInfo {
    private String fileName;
    private String packageName;
    private List<String> importName;

    private List<XmlColumnsInfo> columnsInfo;

    private String dir;
    private String projectPath;
    private String htmlModelName;
}
