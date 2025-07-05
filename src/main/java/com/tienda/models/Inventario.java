package com.tienda.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.tienda.validation.ValidarStockMaximo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidarStockMaximo
@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private int idInventario;

    @Column(name = "fecha_ingreso", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Introduzca la fecha de ingreso del prodcuto")
    private LocalDate fechaIngreso;    

    @Column(name = "cantidad_ingresada", nullable = false)
    @NotNull(message = "La cantidad es requerida")
	@Min(value = 5, message = "La cantidad debe ser como mínimo 5")
    private Integer cantidadIngresada;

    @Column(name = "precio_costo", nullable = false)
    @NotNull(message = "El precio es requerido")
	@Positive(message = "El precio no puede ser negativo")
    private Double precioCosto;    

    @ManyToOne
    @JoinColumn(name = "id_producto")
    @NotNull(message = "Seleccione un producto")
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_producto")
    @NotNull(message = "Seleccione el tipo de producto")
    private TipoProducto tipoProducto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    @NotNull(message = "Seleccione el proveedor")
    private Proveedor proveedor;
        
}

