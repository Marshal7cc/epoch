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
@Table(name = "sys_prompt")
public class SysPrompt extends BaseEntity {

    /**
     * 主键
     */
    @Id
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
