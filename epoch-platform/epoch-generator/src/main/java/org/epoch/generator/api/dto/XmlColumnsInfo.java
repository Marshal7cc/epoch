package org.epoch.generator.api.dto;

import lombok.Data;

/**
 * 用于生成Mapper.xml 记录每一列的属性
 *
 * @author Marshal
 */
@Data
public class XmlColumnsInfo {
    private String tableColumnsName;
    private String dbColumnsName;
    private String jdbcType;
}
