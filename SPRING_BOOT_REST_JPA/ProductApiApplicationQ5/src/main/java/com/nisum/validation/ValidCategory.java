package com.nisum.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidCategory {
    String message() default "Invalid category. Allowed values: Electronics, Clothing, Books, Food";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
