package org.epoch.core.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * <p>
 * 字符串通用校验，校验字段必须不等于某个值或者不属于指定集合
 * </p>
 *
 * @author Marshal
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
@Constraint(validatedBy = NotEqualConstraint.class)
public @interface NotEqual {
    String message() default "{org.epoch.core.validation.NotEqual.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] values() default {};

    /**
     * Defines several {@link NotEqual} annotations on the same element.
     *
     * @see NotEqual
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotEqual[] value();
    }
}
