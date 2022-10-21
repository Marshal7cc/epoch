package org.epoch.iam.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.epoch.data.domain.BaseDO;

/**
 * @author Marshal
 */
@Data
public class RoleDTO extends BaseDO<Long> {
    private String code;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startActiveDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endActiveDate;
}
