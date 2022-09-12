package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class  CheckStatus implements ConstraintValidator< Check, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        return value.equals("nextStatus") || value.equals("cancel");
    }

}