package org.epoch.iam.domain.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.epoch.mybatis.domain.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

;

/**
 * @author Marshal
 */
@Data
@Table(name = "sys_lang")
public class SysLang extends BaseEntity {

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
