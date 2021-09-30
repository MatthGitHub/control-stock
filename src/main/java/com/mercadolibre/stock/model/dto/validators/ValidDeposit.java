package com.mercadolibre.stock.model.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepositValidator.class)
@Documented
public @interface ValidDeposit {
    String message() default "{api.error.internal.format}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
