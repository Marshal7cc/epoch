package org.epoch.jpa.domain;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.data.domain.Versionable;

/**
 * @author Marshal
 * @since 2022/7/13
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class VersionableAuditEntity<ID extends Serializable> extends SimpleAuditEntity<ID> implements Versionable {
    @Version
    private Integer objectVersion;
}
