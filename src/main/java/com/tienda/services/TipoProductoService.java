package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.models.TipoProducto;
import com.tienda.repositories.ITipoProductoRepository;

@Service
public class TipoProductoService {

	@Autowired
	private ITipoProductoRepository _tipoProductoRepository;
	
	public List<TipoProducto> getAll() {
		return _tipoProductoRepository.findAll();
	}
}
