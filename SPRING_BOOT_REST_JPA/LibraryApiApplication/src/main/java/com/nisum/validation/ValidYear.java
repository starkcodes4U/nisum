package com.nisum.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYear {
    String message() default "Year must be a valid 4-digit year (e.g. 2000)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
