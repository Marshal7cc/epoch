package org.epoch.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.data.domain.Statusable;
import org.epoch.data.domain.Versionable;
import org.epoch.jpa.annotation.LogicDelete;
import org.springframework.data.annotation.Version;

/**
 * @author Marshal
 * @since 2022/7/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseAuditEntity<ID> extends SimpleAuditEntity<ID> implements Statusable, Versionable {
    @Version
    private String objectVersion;
    @LogicDelete
    private String status;
}
