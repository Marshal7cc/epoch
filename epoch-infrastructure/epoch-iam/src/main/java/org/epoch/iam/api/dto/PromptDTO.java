package org.epoch.iam.api.dto;

import lombok.Data;
import org.epoch.web.domain.BaseDTO;
import org.hibernate.validator.constraints.Length;


/**
 * @author Marshal
 */
@Data
public class PromptDTO extends BaseDTO {

    /**
     * 主键
     */
    private Long promptId;

    /**
     * 描述编码
     */
    @Length(max = 255)
    private String promptCode;

    /**
     * 语言
     */
    @Length(max = 10)
    private String lang;

    /**
     * 描述
     */
    @Length(max = 240)
    private String description;

}
