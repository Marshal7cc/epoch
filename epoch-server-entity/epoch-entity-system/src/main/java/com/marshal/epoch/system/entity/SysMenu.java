package com.marshal.epoch.system.entity;

import lombok.Data;;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;

import com.marshal.epoch.common.dto.BaseDto;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Table(name = "sys_menu")
public class SysMenu extends BaseDto {

    @Id
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
    @NotEmpty
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
