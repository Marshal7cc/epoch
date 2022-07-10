package org.epoch.data.domain;

import java.time.LocalDateTime;

/**
 * @author Marshal
 * @since 2022/7/9
 */
public interface Auditable<ID> extends Persistable<ID> {
    String FIELD_CREATED_BY = "createdBy";
    String FIELD_CREATED_DATE = "createdDate";
    String FIELD_UPDATED_BY = "updatedBy";
    String FIELD_UPDATED_DATE = "updatedDate";

    /**
     * Returns the user who created this entity.
     *
     * @return the createdBy
     */
    String getCreatedBy();

    /**
     * Sets the user who created this entity.
     *
     * @param createdBy the creating entity to set
     */
    void setCreatedBy(String createdBy);

    /**
     * Returns the creation date of the entity.
     *
     * @return the createdDate
     */
    LocalDateTime getCreatedDate();

    /**
     * Sets the creation date of the entity.
     *
     * @param createdDate the creation date to set
     */
    void setCreatedDate(LocalDateTime createdDate);

    /**
     * Returns the user who modified the entity lastly.
     *
     * @return the lastUpdatedBy
     */
    String getUpdatedBy();

    /**
     * Sets the user who modified the entity lastly.
     *
     * @param updatedBy the last modifying entity to set
     */
    void setUpdatedBy(String updatedBy);

    /**
     * Returns the date of the last modification.
     *
     * @return the lastUpdatedDate
     */
    LocalDateTime getUpdatedDate();

    /**
     * Sets the date of the last modification.
     *
     * @param updatedDate the date of the last modification to set
     */
    void setUpdatedDate(LocalDateTime updatedDate);
}
