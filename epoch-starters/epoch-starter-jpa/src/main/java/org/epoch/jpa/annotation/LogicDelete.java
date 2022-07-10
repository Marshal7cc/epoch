package org.epoch.jpa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author Marshal
 * @since 2022/7/10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Where(clause = "status = '1")
@SQLDelete(sql = "update #{#entityName} set status = '0' where id = ? ")
public @interface LogicDelete {
}
