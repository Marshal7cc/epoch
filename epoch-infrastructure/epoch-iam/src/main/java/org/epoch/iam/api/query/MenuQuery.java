package org.epoch.iam.api.query;

import lombok.Data;
import org.epoch.web.domain.BaseQuery;
import org.hibernate.validator.constraints.Length;


/**
 * @author Marshal
 */
@Data
public class MenuQuery extends BaseQuery {

    private Long menuId;

    /**
     * 菜单编码
     */
    @Length(max = 30)
    private String menuCode;

    /**
     * 菜单名称
     */
    @Length(max = 30)
    private String menuName;

    /**
     * 菜单图标
     */
    @Length(max = 30)
    private String menuIcon;

    /**
     * 模块名称
     */
    @Length(max = 20)
    private String moduleCode;

    /**
     * 组件
     */
    @Length(max = 20)
    private String component;

    /**
     * 描述
     */
    @Length(max = 240)
    private String description;

    /**
     * 路径
     */
    private String path;

    /**
     * 父级菜单id
     */
    private Long parentId;

    /**
     * 排序号
     */
    private Long sequence;

    /**
     * 是否启用
     */
    @Length(max = 1)
    private String enableFlag;

}
