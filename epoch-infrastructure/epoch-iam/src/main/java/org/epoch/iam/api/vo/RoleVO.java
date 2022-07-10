package org.epoch.iam.api.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.epoch.web.facade.vo.BaseVO;

/**
 * @author Marshal
 */
@Data
public class RoleVO extends BaseVO {
    private String code;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startActiveDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endActiveDate;
}
