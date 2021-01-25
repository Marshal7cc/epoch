package org.epoch.generator.api.dto;

import lombok.Data;

/**
 * 数据库列信息
 *
 * @author Marshal
 * @date 2019/5/18
 */
@Data
public class DbColumn {

    private String name;

    private boolean isId = false;

    private boolean isNotEmpty = false;

    private boolean isNotNull = false;

    private String javaType;

    private String jdbcType;

    private String columnLength;

    private String remarks;
}
