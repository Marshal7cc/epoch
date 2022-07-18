package org.epoch.core.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * <p>
 * 字符串通用校验，校验字段必须不等于某个值
 * </p>
 *
 * @author Marshal
 */
public class NotEqualConstraint implements ConstraintValidator<NotEqual, String> {
    private Set<String> values = null;

    @Override
    public void initialize(NotEqual constraintAnnotation) {
        values = new HashSet<>(Arrays.asList(constraintAnnotation.values()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return values == null || !values.contains(value);
    }
}
