package org.epoch.mybatis.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import tk.mybatis.mapper.annotation.Version;

/**
 * @author Marshal
 * @date 2019/11/1
 */
@Data
public class BaseEntity implements Serializable {
    public static String FILED_CREATED_BY = "createdBy";
    public static String FILED_CREATED_DATE = "createdDate";
    public static String FILED_LAST_UPDATED_BY = "updatedBy";
    public static String FILED_LAST_UPDATE_DATE = "updatedDate";
    /* common fields */
//    @KeySql(g)
    protected String id;
    protected String createdBy;
    protected String updatedBy;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected String status;
    @Version
    private String objectVersion;
}
