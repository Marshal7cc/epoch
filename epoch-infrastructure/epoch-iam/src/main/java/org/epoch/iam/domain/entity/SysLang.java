package org.epoch.iam.domain.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.epoch.mybatis.domain.BaseDomain;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

;

/**
 * @author Marshal
 */
@Data
@Table(name = "sys_lang")
public class SysLang extends BaseDomain {

    @Id
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
