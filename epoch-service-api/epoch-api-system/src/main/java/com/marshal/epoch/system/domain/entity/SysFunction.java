package com.marshal.epoch.system.domain.entity;

import com.marshal.epoch.mybatis.domain.AuditDomain;
import lombok.Data;;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;


/**
 * @author Marshal
 */
@Data
@Table(name = "sys_function")
public class SysFunction extends AuditDomain {

    /**
     * 主键
     */
    @Id
    private Long functionId;

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
     * 资源类型
     */
    @Length(max = 15)
    private String type;

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
