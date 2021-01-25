package org.epoch.security.entity;

import org.epoch.mybatis.annotation.AuditRecord;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Marshal
 * @date 2020/1/17
 */
@AuditRecord(enabled = false)
@Table(name = "oauth_access_token")
public class OauthAccessToken {
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * 客户端ID
     */
    @Column(name = "CLIENT_ID")
    private String clientId;

    /**
     * token
     */
    @Column(name = "TOKEN")
    private String token;

    /**
     * token获取日期
     */
    @Column(name = "TOKEN_ACCESS_TIME")
    private Date tokenAccessTime;

    /**
     * token获取方式
     */
    @Column(name = "TOKEN_ACCESS_TYPE")
    private String tokenAccessType;

    /**
     * token失效日期
     */
    @Column(name = "TOKEN_EXPIRES_TIME")
    private Date tokenExpiresTime;

    /**
     * 是否有效
     */
    @Column(name = "REVOKE_FLAG")
    private String revokeFlag;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取客户端ID
     *
     * @return CLIENT_ID - 客户端ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置客户端ID
     *
     * @param clientId 客户端ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取token
     *
     * @return TOKEN - token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token
     *
     * @param token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取token获取日期
     *
     * @return TOKEN_ACCESS_TIME - token获取日期
     */
    public Date getTokenAccessTime() {
        return tokenAccessTime;
    }

    /**
     * 设置token获取日期
     *
     * @param tokenAccessTime token获取日期
     */
    public void setTokenAccessTime(Date tokenAccessTime) {
        this.tokenAccessTime = tokenAccessTime;
    }

    /**
     * 获取token获取方式
     *
     * @return TOKEN_ACCESS_TYPE - token获取方式
     */
    public String getTokenAccessType() {
        return tokenAccessType;
    }

    /**
     * 设置token获取方式
     *
     * @param tokenAccessType token获取方式
     */
    public void setTokenAccessType(String tokenAccessType) {
        this.tokenAccessType = tokenAccessType;
    }

    /**
     * 获取token失效日期
     *
     * @return TOKEN_EXPIRES_TIME - token失效日期
     */
    public Date getTokenExpiresTime() {
        return tokenExpiresTime;
    }

    /**
     * 设置token失效日期
     *
     * @param tokenExpiresTime token失效日期
     */
    public void setTokenExpiresTime(Date tokenExpiresTime) {
        this.tokenExpiresTime = tokenExpiresTime;
    }

    /**
     * 获取是否有效
     *
     * @return REVOKE_FLAG - 是否有效
     */
    public String getRevokeFlag() {
        return revokeFlag;
    }

    /**
     * 设置是否有效
     *
     * @param revokeFlag 是否有效
     */
    public void setRevokeFlag(String revokeFlag) {
        this.revokeFlag = revokeFlag;
    }
}
