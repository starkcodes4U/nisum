package com.nisum.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

    private final List<String> validCategories = List.of("Electronics", "Clothing", "Books", "Food");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && validCategories.contains(value);
    }
}