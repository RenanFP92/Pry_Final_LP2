package com.tienda.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tienda.models.Inventario;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {
	
    List<Inventario> findAllByOrderByIdInventarioDesc();
    
    List<Inventario> findAllByTipoProducto_IdTipoProductoOrderByIdInventarioDesc(Integer idTipoProducto);
    
    @Query("""
    		select i from Inventario i
    		where
    			(:idTipoProducto is null or i.tipoProducto.idTipoProducto = :idTipoProducto)
    			and
    			(:idProveedor is null or i.proveedor.idProveedor = :idProveedor)
    		order by
    			i.idInventario desc
    		""")
   List<Inventario> findAllWithFilters(@Param("idTipoProducto") Integer idTipoProducto, @Param("idProveedor") Integer idProveedor);
    
    Inventario findByProducto_IdProducto(Integer idProducto);
}
