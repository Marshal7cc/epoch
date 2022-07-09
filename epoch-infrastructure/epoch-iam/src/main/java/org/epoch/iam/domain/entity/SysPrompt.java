package org.epoch.iam.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.epoch.mybatis.domain.BaseAuditEntity;
import org.hibernate.validator.constraints.Length;

;

/**
 * @author Marshal
 */
@Data
@TableName( "sys_prompt")
public class SysPrompt extends BaseAuditEntity<SysPrompt,Long> {

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
