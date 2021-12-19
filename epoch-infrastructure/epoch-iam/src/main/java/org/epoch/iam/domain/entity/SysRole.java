package org.epoch.iam.domain.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.epoch.starter.mybatis.domain.entity.BaseAuditEntity;

/**
 * @author Marshal
 */
@Data
@Table(name = "sys_role")
public class SysRole extends BaseAuditEntity {
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "START_ACTIVE_DATE")
    private Date startActiveDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "END_ACTIVE_DATE")
    private Date endActiveDate;

}
