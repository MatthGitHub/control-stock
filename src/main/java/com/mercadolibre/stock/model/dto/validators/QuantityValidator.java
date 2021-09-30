package com.mercadolibre.stock.model.dto.validators;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class QuantityValidator implements ConstraintValidator<ValidQuantity, Integer> {
    @Value("${spring.application.location.items.total}")
    private int LOCATION_ITEMS_TOTAL;

    @Override
    public void initialize(ValidQuantity validQuantity) {
    }

    @Override
    public boolean isValid(Integer quantity, ConstraintValidatorContext context){
        if(Objects.nonNull(quantity))
            return (quantity >= 0 && quantity <= LOCATION_ITEMS_TOTAL);

        return false;
    }
}