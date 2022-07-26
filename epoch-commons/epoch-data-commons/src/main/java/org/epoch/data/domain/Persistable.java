package org.epoch.data.domain;

import org.springframework.data.annotation.Transient;

/**
 * @author Marshal
 * @since 2022/7/10
 */
public interface Persistable<ID> extends org.springframework.data.domain.Persistable<ID> {
    String FIELD_ID = "id";

    /**
     * Returns if the {@code Persistable} is new or was persisted already.
     *
     * @return if {@literal true} the object is new.
     */
    @Transient
    @Override
    default boolean isNew() {
        return this.getId() == null;
    }
}
