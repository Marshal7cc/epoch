package org.epoch.mybatis.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Id;

import lombok.Data;
import org.epoch.mybatis.helper.SnowflakeKeyGenerator;
import org.epoch.mybatis.version.ObjectNextVersion;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.Version;

/**
 * @author Marshal
 * @date 2019/11/1
 */
@Data
public class BaseAuditEntity implements Serializable {
    public static String FILED_ID = "id";
    public static String FILED_STATUS = "status";
    public static String FILED_CREATED_BY = "createdBy";
    public static String FILED_CREATED_DATE = "createdDate";
    public static String FILED_LAST_UPDATED_BY = "updatedBy";
    public static String FILED_LAST_UPDATE_DATE = "updatedDate";
    public static String FILED_OBJECT_VERSION = "objectVersion";
    @Id
    @KeySql(genId = SnowflakeKeyGenerator.class)
    protected String id;
    protected String createdBy;
    protected String updatedBy;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected String status;
    @Version(nextVersion = ObjectNextVersion.class)
    private String objectVersion;
}
