package org.epoch.data.annotation.comparator;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Marshal
 * @since 2022/7/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = FIELD)
public @interface Null {
}
