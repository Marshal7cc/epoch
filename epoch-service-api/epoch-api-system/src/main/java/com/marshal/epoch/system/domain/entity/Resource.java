package com.marshal.epoch.system.domain.entity;

import com.marshal.epoch.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Marshal
 */
@Data
@Table(name = "sys_resource")
public class Resource extends AuditDomain {

    private static final String RESOURCE_ID = "resource_id";
    private static final String MENU_ID = "menu_id";
    private static final String URL = "url";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LOGIN_REQUIRE = "login_require";
    private static final String ACCESS_CHECK = "access_check";

    @Id
    @ApiModelProperty(value="主键")
    private Long resourceId;

    @ApiModelProperty(value="所属菜单")
    private Long menuId;

    @ApiModelProperty(value="URL")
    private String url;

    @ApiModelProperty(value="资源名称")
    private String name;

    @ApiModelProperty(value="描述")
    private String description;

    @ApiModelProperty(value="是否需要登录")
    private String loginRequire;

    @ApiModelProperty(value="是否权限校验")
    private String accessCheck;

}
