package org.epoch.data.domain;

import lombok.Data;

/**
 * @author Marshal
 * @since 2022/7/9
 */
@Data
public abstract class AbstractDO<ID> implements DomainObject<ID> {
    private ID id;
    private Integer objectVersion;
}
