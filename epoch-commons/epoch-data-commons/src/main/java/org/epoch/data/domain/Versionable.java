package org.epoch.data.domain;

/**
 * @author Marshal
 * @since 2022/7/9
 */
public interface Versionable {
    String FIELD_VERSION = "objectVersion";

    /**
     * Returns the version of this entity.
     *
     * @return the status
     */
    Integer getObjectVersion();

    /**
     * Sets the status of this entity.
     *
     * @param objectVersion the version of this entity
     */
    void setObjectVersion(Integer objectVersion);
}
