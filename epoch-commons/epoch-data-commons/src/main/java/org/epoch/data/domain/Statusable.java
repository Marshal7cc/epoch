package org.epoch.data.domain;

/**
 * @author Marshal
 * @since 2022/7/10
 */
public interface Statusable {
    String FIELD_STATUS = "status";

    /**
     * Returns the status of this entity.
     *
     * @return the status
     */
    String getStatus();

    /**
     * Sets the status of this entity.
     *
     * @param status the status of this entity
     */
    void setStatus(String status);
}
