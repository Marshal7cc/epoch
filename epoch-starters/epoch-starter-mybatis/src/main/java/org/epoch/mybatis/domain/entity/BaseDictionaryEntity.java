package org.epoch.mybatis.domain.entity;

import lombok.Data;

/**
 * @author Marshal
 * @date 2021/3/7
 */
@Data
public class BaseDictionaryEntity extends BaseAuditEntity {
    public static String FILED_TENANT_ID = "tenantId";
    public static String FILED_CODE = "code";
    public static String FILED_NAME = "name";
    public static String FILED_DESCRIPTION = "description";
    protected String tenantId;
    protected String code;
    protected String name;
    protected String description;
}
