package com.arakan.carlicense.license;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import jakarta.validation.constraints.*;


@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {

    String message() default
            "Expiry date must not be before issue date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
