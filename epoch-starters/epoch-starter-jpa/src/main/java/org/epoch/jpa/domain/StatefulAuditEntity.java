package org.epoch.jpa.domain;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.data.domain.Stateful;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author Marshal
 * @since 2022/7/13
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@Where(clause = "status ='1'")
@SQLDelete(sql = "update #{#entityName} set status = '0' where id = ?")
public class StatefulAuditEntity<ID extends Serializable> extends SimpleAuditEntity<ID> implements Stateful {
    private String status;
}
