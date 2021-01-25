package org.epoch.generator.api.dto;


import lombok.Data;

import java.util.List;

/**
 * 模板文件信息
 *
 * @author Marshal
 * @date 2019/5/18
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
