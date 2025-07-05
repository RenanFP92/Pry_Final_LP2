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
@Table(name= "proveedor")
public class Proveedor {

    @Id
    @Column(name ="id_proveedor") 
    private int idProveedor;

    @Column(name ="nombre_empresa", nullable = false)
    private String nombreEmpresa;

    @Column(name ="contacto_proveedor") 
    private String contactoProveedor;

    @Column(name ="telefono") 
    private String telefono;

    @Column(name ="correo") 
    private String correo;

    @Override
    public String toString() {
        return nombreEmpresa;
    }
}