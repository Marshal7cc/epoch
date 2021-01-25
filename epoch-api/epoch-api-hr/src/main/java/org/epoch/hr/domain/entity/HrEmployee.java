package org.epoch.hr.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

import org.epoch.mybatis.domain.BaseDomain;

import java.util.Date;


/**
 * @author Marshal
 */
@Data
@Table(name = "hr_employee")
public class HrEmployee extends BaseDomain {

    @Id
    private Long employeeId;

    /**
     * 员工编码
     */
    @Length(max = 30)
    private String employeeCode;

    /**
     * 员工姓名
     */
    @Length(max = 50)
    private String name;

    private Long positionId;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bornDate;

    /**
     * 电子邮件
     */
    @Length(max = 50)
    private String email;

    /**
     * 移动电话
     */
    @Length(max = 50)
    private String mobile;

    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date joinDate;

    /**
     * 性别
     */
    @Length(max = 1)
    private String gender;

    /**
     * id
     */
    @Length(max = 100)
    private String certificateId;

    /**
     * 状态
     */
    @Length(max = 50)
    private String status;

    /**
     * 证件类型
     */
    @Length(max = 240)
    private String certificateType;

    /**
     * 是否启用
     */
    @Length(max = 1)
    private String enableFlag;

}
