package org.epoch.hr.domain.entity;

import org.epoch.mybatis.domain.BaseDomain;
import lombok.Data;;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;


import java.util.Date;


/**
 * @author Marshal
 */
@Data
@Table(name = "hr_company")
public class HrCompany extends BaseDomain {

    /**
     * 主键
     */
    @Id
    private Long companyId;

    /**
     * 公司编码
     */
    @Length(max = 30)
    private String companyCode;

    /**
     * 公司类型
     */
    @Length(max = 30)
    private String companyType;

    /**
     * 地址
     */
    @Length(max = 250)
    private String address;

    /**
     * 公司级别
     */
    private Long companyLevelId;

    /**
     * 母公司
     */
    private Long parentCompanyId;

    /**
     * 主岗位
     */
    private Long chiefPositionId;

    /**
     * 有效时间开始
     */
    private Date startDateActive;

    /**
     * 有效时间截止
     */
    private Date endDateActive;

    /**
     * 公司简称
     */
    @Length(max = 250)
    private String companyShortName;

    /**
     * 公司全称
     */
    @Length(max = 250)
    private String companyFullName;

    /**
     * 邮编
     */
    @Length(max = 100)
    private String zipcode;

    /**
     * 传真
     */
    @Length(max = 100)
    private String fax;

    /**
     * 联系电话
     */
    @Length(max = 100)
    private String phone;

    /**
     * 联系人
     */
    @Length(max = 100)
    private String contactPerson;

    /**
     * 是否启用
     */
    @Length(max = 1)
    private String enableFlag;

}
