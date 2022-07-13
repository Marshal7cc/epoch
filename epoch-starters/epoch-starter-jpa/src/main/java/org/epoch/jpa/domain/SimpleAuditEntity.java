package org.epoch.jpa.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Data;
import org.epoch.data.domain.Auditable;
import org.epoch.jpa.convert.LocalDateTimeConverter;
import org.epoch.jpa.domain.support.SnowflakeKeyGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Marshal
 * @since 2022/7/9
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SimpleAuditEntity<ID extends Serializable> implements Auditable<ID> {
    @Id
    @GeneratedValue(generator = SnowflakeKeyGenerator.GENERATOR_NAME)
    @GenericGenerator(name = SnowflakeKeyGenerator.GENERATOR_NAME, strategy = SnowflakeKeyGenerator.GENERATOR_REFERENCE)
    private ID id;
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedDate;

    @Override
    public ID getId() {
        return id;
    }
}
