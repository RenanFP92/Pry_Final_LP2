package com.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.models.TipoProducto;

public interface ITipoProductoRepository extends JpaRepository<TipoProducto, Integer>{

}
