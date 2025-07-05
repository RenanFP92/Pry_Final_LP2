package com.tienda.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usua")
	private Integer idUsuario;
	
	@Column(name = "nom_usua", nullable = false)
	private String nombreUsuario;
	
	@Column(name = "ape_usua", nullable = false)
	private String apellidoUsuario;

	@Column(name = "user_usua", nullable = false)
	private String usuario;

	@Column(name = "pswd_usua", nullable = false)
	private String passwordUsuario;

	@Column(name = "fnac_usua", nullable = false)
	private LocalDate fechaNacimiento;

	@ManyToOne
	@JoinColumn(name = "idtipousua", columnDefinition = "INT NOT NULL DEFAULT 2")
	private TipoUsuario tipoUsuario;

	@Column(name = "est_usua", columnDefinition = "BIT NOT NULL DEFAULT 1")
	private Boolean estado;

	public String getFullUsuario() {
		return String.format("%s - %s %s", usuario, nombreUsuario, apellidoUsuario);
	}
}
