package com.tienda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_tipos_usuarios")
public class TipoUsuario {
	
	@Id
	@Column(name = "idtipousua")
	private int idTipoUsuario;
	
	@Column(name = "rol")
	private String rol;
}
