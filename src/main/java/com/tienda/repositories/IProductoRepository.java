package com.tienda.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.models.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findAllByEstado(Integer estado);
}