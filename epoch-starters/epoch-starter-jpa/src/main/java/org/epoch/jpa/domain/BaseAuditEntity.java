package org.epoch.jpa.domain;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.data.domain.Statusable;
import org.epoch.data.domain.Versionable;

/**
 * @author Marshal
 * @since 2022/7/10
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class BaseAuditEntity<ID extends Serializable> extends SimpleAuditEntity<ID> implements Statusable, Versionable {
    @Version
    private Integer objectVersion;
    private String status;
}
