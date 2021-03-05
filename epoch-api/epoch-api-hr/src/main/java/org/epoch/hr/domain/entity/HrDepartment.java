package org.epoch.hr.domain.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.epoch.mybatis.domain.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

;


/**
 * @author Marshal
 */
@Data
@Table(name = "hr_department")
public class HrDepartment extends BaseEntity {

    /**
     * 主键
     */
    @Id
    private Long departmentId;

    /**
     * 父部门
     */
    private Long parentId;

    /**
     * 部门编码
     */
    @Length(max = 50)
    private String departmentCode;

    /**
     * 部门名称
     */
    @Length(max = 100)
    private String name;

    /**
     * 部门描述
     */
    @Length(max = 255)
    private String description;

    /**
     * 部门管理岗位
     */
    private Long managerPosition;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 部门分类
     */
    @Length(max = 50)
    private String departmentType;

    /**
     * 是否启用
     */
    @Length(max = 1)
    private String enableFlag;

}
