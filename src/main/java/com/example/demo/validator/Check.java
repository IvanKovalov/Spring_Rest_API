package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CheckStatus.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
    String message() default "Invalid status of task: type next or cancelled";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
