package com.nisum.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearValidator implements ConstraintValidator<ValidYear, String> {
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("\\d{4}");
    }
}
