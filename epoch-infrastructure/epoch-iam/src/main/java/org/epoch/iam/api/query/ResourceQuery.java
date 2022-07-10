package org.epoch.iam.api.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.epoch.web.facade.query.BaseQuery;

/**
 * @author Marshal
 */
@Data
public class ResourceQuery extends BaseQuery {

    @ApiModelProperty(value = "主键")
    private Long resourceId;

    @ApiModelProperty(value = "所属菜单")
    private Long menuId;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否需要登录")
    private String loginRequire;

    @ApiModelProperty(value = "是否权限校验")
    private String accessCheck;

}
