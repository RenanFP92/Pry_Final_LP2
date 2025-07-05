package com.tienda.models;

import com.tienda.dtos.DetalleBoletaId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "tb_det_boleta")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DetalleBoletaId.class)
public class DetalleBoleta {

	@Id
	@ManyToOne
	@JoinColumn(name = "num_bol")
	private Boleta boleta;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@Column(name = "preciovta", nullable = false)
	private Double precioVenta;
}
