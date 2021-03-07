package org.epoch.hr.domain.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.epoch.mybatis.domain.entity.BaseAuditEntity;
import org.hibernate.validator.constraints.Length;

;


/**
 * @author Marshal
 */
@Data
@Table(name = "hr_position")
public class HrPosition extends BaseAuditEntity {

    /**
     * 主键
     */
    @Id
    private Long positionId;

    /**
     * 部门id
     */
    @NotNull
    private Long departmentId;

    /**
     * 岗位编码
     */
    @Length(max = 50)
    private String positionCode;

    /**
     * 岗位名称
     */
    @Length(max = 100)
    private String name;

    /**
     * 岗位描述
     */
    @Length(max = 255)
    private String description;

    /**
     * 父岗位id
     */
    private Long parentPositionId;

    /**
     * 是否启用
     */
    @Length(max = 1)
    private String enableFlag;

}
