package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dtos.InventarioFilter;
import com.tienda.dtos.ResultadoResponse;
import com.tienda.models.Inventario;
import com.tienda.models.Producto;
import com.tienda.repositories.IInventarioRepository;
import com.tienda.repositories.IProductoRepository;

@Service
public class InventarioService {
	
	@Autowired
	IInventarioRepository _inventarioRepository;
	
	@Autowired
	IProductoRepository _productoRepository;
	
	//Listado
	public List<Inventario> getAll(){
		return _inventarioRepository.findAllByOrderByIdInventarioDesc();
	}
	
	//Filtros de búsqueda
	public List<Inventario> search(InventarioFilter filtro) {
		return _inventarioRepository.findAllWithFilters(filtro.getIdTipoProducto(), filtro.getIdProveedor());
	}	
	
	//Adicionar algo al inventario	
	public ResultadoResponse create(Inventario inventario) {
	    try {
	        // 1. Obtener el producto actual desde la BD
	        Producto producto = _productoRepository.findById(inventario.getProducto().getIdProducto())
	                                 .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

	        // 2. Sumar la cantidad ingresada al stock actual del producto
	        int nuevoStock = producto.getStockActual() + inventario.getCantidadIngresada();
	        producto.setStockActual(nuevoStock);

	        // 3. Guardar el producto actualizado
	        _productoRepository.save(producto);

	        // 4. Guardar el inventario
	        Inventario registrado = _inventarioRepository.save(inventario);

	        String mensaje = String.format("Inventario registrado correctamente. Stock actualizado a %d", nuevoStock);
	        return new ResultadoResponse(true, mensaje);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new ResultadoResponse(false, "Error al registrar inventario: " + ex.getMessage());
	    }
	}

	
	//Obtener ID para editar
	public Inventario getOne(Integer id) {
		return _inventarioRepository.findById(id).orElseThrow();
	}
	
	//Edita algún inventario ya creado
	public ResultadoResponse update(Inventario inventario) {
	    try {
	        // 1. Buscar el inventario original
	        Inventario inventarioOriginal = _inventarioRepository.findById(inventario.getIdInventario())
	            .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

	        // 2. Obtener el producto relacionado
	        Producto producto = _productoRepository.findById(inventario.getProducto().getIdProducto())
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

	        // 3. Calcular diferencia de cantidad
	        int diferencia = inventario.getCantidadIngresada() - inventarioOriginal.getCantidadIngresada();

	        // 4. Actualizar el stockActual del producto según la diferencia
	        int nuevoStock = producto.getStockActual() + diferencia;
	        producto.setStockActual(nuevoStock);

	        // 5. Guardar el producto actualizado
	        _productoRepository.save(producto);

	        // 6. Guardar los cambios del inventario
	        _inventarioRepository.save(inventario);

	        String mensaje = String.format("Inventario actualizado. Stock ahora: %d", nuevoStock);
	        return new ResultadoResponse(true, mensaje);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new ResultadoResponse(false, "Error al actualizar inventario: " + ex.getMessage());
	    }
	}

	
}
