package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.models.EstadoProducto;
import com.tienda.models.Producto;
import com.tienda.repositories.IProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private IProductoRepository _productoRepository;
	
	public List<Producto> getAll() {
		return _productoRepository.findAll();
	}	
	
	public List<Producto> getActivos() {
	    return _productoRepository.findAllByEstado(EstadoProducto.ACTIVO);
	}
	
	public Producto getOne(Integer id) {
	    return _productoRepository.findById(id).orElseThrow();
	}
}
