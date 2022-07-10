package org.epoch.iam.api.query;

import lombok.Data;
import org.epoch.web.facade.query.BaseQuery;
import org.hibernate.validator.constraints.Length;


/**
 * @author Marshal
 */
@Data
public class LangQuery extends BaseQuery {

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
