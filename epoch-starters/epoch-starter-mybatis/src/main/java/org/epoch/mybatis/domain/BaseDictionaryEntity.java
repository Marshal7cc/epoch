package org.epoch.mybatis.domain;

import lombok.Data;

/**
 * @author Marshal
 * @date 2021/3/7
 */
@Data
public class BaseDictionaryEntity extends SimpleAuditEntity<BaseDictionaryEntity, String> {
    public static String FILED_TENANT_ID = "tenantId";
    public static String FILED_CODE = "code";
    public static String FILED_NAME = "name";
    public static String FILED_DESCRIPTION = "description";
    protected String tenantId;
    protected String code;
    protected String name;
    protected String description;
}
