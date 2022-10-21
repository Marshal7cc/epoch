package org.epoch.iam.facade.query;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.epoch.web.facade.query.BaseQuery;

/**
 * @author Marshal
 */
@Data
public class RoleQuery extends BaseQuery {
    private String code;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startActiveDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endActiveDate;
}
