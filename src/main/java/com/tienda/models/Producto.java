package com.tienda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
    
    @Id
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "stock_maximo")
    private int stockMaximo;

    @Column(name = "stock_actual")
    private int stockActual;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto")
    private TipoProducto tipoProducto;
    
    @Transient
    public int getStockFaltante() {
        return this.stockMaximo - this.stockActual;
    }
    
    public String getNomEstado() {
		switch (this.estado) {
		case 0:
			return "Inactivo";
		case 1:
			return "Activo";
		default:
			return "Desconocido";
		}
	}
}