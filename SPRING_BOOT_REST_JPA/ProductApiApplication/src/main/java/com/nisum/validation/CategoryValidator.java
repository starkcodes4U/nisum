package com.nisum.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

    private static final Set<String> ALLOWED = Set.of("electronics", "books", "clothing");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ALLOWED.contains(value.toLowerCase());
    }
}
