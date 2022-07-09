package org.epoch.iam.domain.entity;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.epoch.mybatis.domain.entity.BaseAuditEntity;
import org.hibernate.validator.constraints.Length;

;

/**
 * @author Marshal
 */
@Data
@Table(name = "sys_user")
public class SysUser extends BaseAuditEntity {

    @Id
    private Long userId;

    /**
     * 用户名
     */
    @Length(max = 40)
    private String userName;

    /**
     * 加密过的密码
     */
    @Length(max = 80)
    private String password;

    /**
     * 邮箱地址
     */
    @Length(max = 150)
    private String email;

    /**
     * 电话号码
     */
    @Length(max = 40)
    private String phone;

    /**
     * 状态
     */
    @Length(max = 40)
    private String status;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginDate;

    /**
     * 最后一次修改密码时间
     */
    private Date lastPasswordUpdateDate;

    /**
     * 是否冻结
     */
    @Length(max = 2)
    private String enableFlag;

    /**
     * 冻结时间
     */
    private Date frozenDate;

    /**
     * 说明
     */
    @Length(max = 255)
    private String description;

    /**
     * 员工ID
     */
    private Long employeeId;

    /**
     * 头像
     */
    @Length(max = 255)
    private String avatar;

    @Length(max = 50)
    private String qqOpenid;

    @Length(max = 50)
    private String wxOpenid;

}
