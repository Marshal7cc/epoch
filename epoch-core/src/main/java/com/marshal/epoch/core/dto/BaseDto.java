package com.marshal.epoch.core.dto;

import tk.mybatis.mapper.annotation.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * @auth: Marshal
 * @date: 2019/11/1
 * @desc: BaseDto
 */
public class BaseDto implements Serializable {

    /**
     * 创建者
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 最后一次更新者
     */
    private Long lastUpdatedBy;

    /**
     * 最后一次更新时间
     */
    private Date lastUpdateDate;

    /**
     * 乐观锁关键字
     */
    @Version
    private Long objectVersion;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(Long objectVersion) {
        this.objectVersion = objectVersion;
    }
}
