package org.epoch.iam.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.epoch.mybatis.domain.SimpleAuditEntity;
import org.hibernate.validator.constraints.Length;


/**
 * @author Marshal
 */
@Data
@TableName("sys_lang")
public class SysLang extends SimpleAuditEntity<SysLang,Long> {

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
