package org.epoch.iam.domain.dto;

import lombok.Data;
import org.epoch.data.domain.BaseDO;
import org.hibernate.validator.constraints.Length;


/**
 * @author Marshal
 */
@Data
public class LangDTO extends BaseDO<Long> {

    private Long langId;

    /**
     * 语言代码
     */
    private String langCode;

    /**
     * 语言名称
     */
    @Length(max = 20)
    private String langName;

    /**
     * 描述
     */
    @Length(max = 240)
    private String description;

    /**
     * 是否启用
     */
    @Length(max = 1)
    private String enableFlag;

}
