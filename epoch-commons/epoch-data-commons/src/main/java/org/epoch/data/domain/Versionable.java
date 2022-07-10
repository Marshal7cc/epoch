package org.epoch.data.domain;

/**
 * @author Marshal
 * @since 2022/7/9
 */
public interface Versionable {
    String FIELD_VERSION = "versionNumber";

    /**
     * Returns the version of this entity.
     *
     * @return the status
     */
    String getVersionNumber();

    /**
     * Sets the status of this entity.
     *
     * @param versionNumber the version of this entity
     */
    void setVersionNumber(String versionNumber);
}
