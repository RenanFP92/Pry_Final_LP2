package com.tienda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_producto")
public class TipoProducto {
	
	@Id
	@Column(name = "id_tipo_producto")
	private int idTipoProducto;
	
	@Column(name = "nombre_tipo")
	private String nombreTipo;
	
	@Override
	public String toString() {
		return nombreTipo;
	}	
}