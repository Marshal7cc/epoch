package org.epoch.iam.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.epoch.mybatis.domain.SimpleAuditEntity;
import org.hibernate.validator.constraints.Length;


/**
 * @author Marshal
 */
@Data
@TableName("sys_resource")
public class SysResource extends SimpleAuditEntity<SysResource,Long> {

    /**
     * 主键
     */
    private Long resourceId;

    /**
     * 所属菜单
     */
    private Long menuId;

    /**
     * URL
     */
    @Length(max = 255)
    private String url;

    /**
     * 资源名称
     */
    @Length(max = 40)
    private String name;

    /**
     * 描述
     */
    @Length(max = 240)
    private String description;

    /**
     * 是否需要登录
     */
    @Length(max = 1)
    private String loginRequire;

    /**
     * 是否权限校验
     */
    @Length(max = 1)
    private String accessCheck;

}
