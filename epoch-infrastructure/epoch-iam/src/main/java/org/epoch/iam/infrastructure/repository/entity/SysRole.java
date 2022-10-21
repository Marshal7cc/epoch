package org.epoch.iam.infrastructure.repository.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.epoch.mybatis.domain.BaseAuditEntity;

/**
 * @author Marshal
 */
@Data
@TableName("sys_role")
public class SysRole extends BaseAuditEntity<SysRole, Long> {
    private String code;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startActiveDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endActiveDate;
}
