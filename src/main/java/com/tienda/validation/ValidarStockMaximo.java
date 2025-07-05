package com.tienda.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StockMaximoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidarStockMaximo {
    String message() default "La cantidad ingresada supera el stock máximo permitido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}