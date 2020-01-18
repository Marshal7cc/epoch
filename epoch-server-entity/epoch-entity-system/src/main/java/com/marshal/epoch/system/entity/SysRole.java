package com.marshal.epoch.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_role")
public class SysRole {
    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 角色编码
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;

    /**
     * 开始生效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "START_ACTIVE_DATE")
    private Date startActiveDate;

    /**
     * 截至生效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "END_ACTIVE_DATE")
    private Date endActiveDate;

    /**
     * 启用标记
     */
    @Column(name = "ENABLE_FLAG")
    private String enableFlag;

    /**
     * @return ROLE_ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色编码
     *
     * @return ROLE_CODE - 角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码
     *
     * @param roleCode 角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色描述
     *
     * @return ROLE_DESCRIPTION - 角色描述
     */
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * 设置角色描述
     *
     * @param roleDescription 角色描述
     */
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    /**
     * 获取开始生效日期
     *
     * @return START_ACTIVE_DATE - 开始生效日期
     */
    public Date getStartActiveDate() {
        return startActiveDate;
    }

    /**
     * 设置开始生效日期
     *
     * @param startActiveDate 开始生效日期
     */
    public void setStartActiveDate(Date startActiveDate) {
        this.startActiveDate = startActiveDate;
    }

    /**
     * 获取截至生效日期
     *
     * @return END_ACTIVE_DATE - 截至生效日期
     */
    public Date getEndActiveDate() {
        return endActiveDate;
    }

    /**
     * 设置截至生效日期
     *
     * @param endActiveDate 截至生效日期
     */
    public void setEndActiveDate(Date endActiveDate) {
        this.endActiveDate = endActiveDate;
    }

    /**
     * 获取启用标记
     *
     * @return ENABLE_FLAG - 启用标记
     */
    public String getEnableFlag() {
        return enableFlag;
    }

    /**
     * 设置启用标记
     *
     * @param enableFlag 启用标记
     */
    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }
}
