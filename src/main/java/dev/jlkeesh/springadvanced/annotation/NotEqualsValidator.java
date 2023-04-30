package dev.jlkeesh.springadvanced.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NotEqualsValidator implements ConstraintValidator<NotEquals, Double> {
    private double value;

    @Override
    public void initialize(NotEquals constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Double actual, ConstraintValidatorContext context) {
        return  !(value == actual);
    }
}
