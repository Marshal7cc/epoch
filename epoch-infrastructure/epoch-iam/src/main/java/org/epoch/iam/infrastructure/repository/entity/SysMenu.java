package org.epoch.iam.infrastructure.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.epoch.mybatis.domain.SimpleAuditEntity;
import org.hibernate.validator.constraints.Length;



/**
 * @author Marshal
 */
@Data
@TableName(  "sys_menu")
public class SysMenu extends SimpleAuditEntity<SysMenu,Long> {

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
