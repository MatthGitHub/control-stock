package com.mercadolibre.stock.model.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuantityValidator.class)
@Documented
public @interface ValidQuantity {
    String message() default "{api.error.internal.data.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
