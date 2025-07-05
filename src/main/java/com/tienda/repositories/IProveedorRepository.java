package com.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.models.Proveedor;

public interface IProveedorRepository extends JpaRepository<Proveedor, Integer>{
	
	
}
