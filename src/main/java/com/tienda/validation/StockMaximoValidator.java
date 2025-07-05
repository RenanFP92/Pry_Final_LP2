package com.tienda.validation;

import com.tienda.models.Inventario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StockMaximoValidator implements ConstraintValidator<ValidarStockMaximo, Inventario> {

    @Override
    public boolean isValid(Inventario inventario, ConstraintValidatorContext context) {
        if (inventario == null || inventario.getProducto() == null) {
            return true; // otra validación lo atrapará
        }
        if (inventario.getProducto().getStockMaximo() == 0) {
            return true; // si aún no se ha seleccionado producto
        }
        int nuevoStock = inventario.getProducto().getStockActual() + inventario.getCantidadIngresada();
        return nuevoStock <= inventario.getProducto().getStockMaximo();
    }
}