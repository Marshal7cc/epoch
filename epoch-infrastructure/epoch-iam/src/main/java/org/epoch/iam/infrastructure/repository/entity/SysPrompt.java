package org.epoch.iam.infrastructure.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.epoch.mybatis.domain.SimpleAuditEntity;
import org.hibernate.validator.constraints.Length;

;

/**
 * @author Marshal
 */
@Data
@TableName( "sys_prompt")
public class SysPrompt extends SimpleAuditEntity<SysPrompt,Long> {

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
