package com.marshal.epoch.system.domain.entity;

import lombok.Data;;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

import com.marshal.epoch.mybatis.domain.AuditDomain;

/**
 * @author Marshal
 */
@Data
@Table(name = "sys_lang")
public class SysLang extends AuditDomain {

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
