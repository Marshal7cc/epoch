package org.epoch.jpa.domain;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import org.epoch.data.domain.Stateful;

/**
 * @author Marshal
 * @since 2022/7/13
 */
@Data
@MappedSuperclass
public class StatefulAuditEntity<ID extends Serializable> extends SimpleAuditEntity<ID> implements Stateful {
    private String status;
}
