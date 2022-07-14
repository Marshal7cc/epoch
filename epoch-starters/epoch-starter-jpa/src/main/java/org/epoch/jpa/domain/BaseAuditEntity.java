package org.epoch.jpa.domain;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.data.domain.Stateful;
import org.epoch.data.domain.Versionable;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author Marshal
 * @since 2022/7/10
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@Where(clause = "status ='1'")
@SQLDelete(sql = "update #{#entityName} set status = '0' where id = ? and object_version = ?")
public class BaseAuditEntity<ID extends Serializable> extends SimpleAuditEntity<ID> implements Stateful, Versionable {
    @Version
    private Integer objectVersion;
    private String status;
}
