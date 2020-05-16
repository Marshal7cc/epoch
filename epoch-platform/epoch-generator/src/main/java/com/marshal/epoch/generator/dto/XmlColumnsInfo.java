package com.marshal.epoch.generator.dto;

import lombok.Data;

/**
 * 用于生成Mapper.xml
 * 记录每一列的属性
 */
@Data
public class XmlColumnsInfo {
    private String tableColumnsName;
    private String dBColumnsName;
    private String jdbcType;
}
