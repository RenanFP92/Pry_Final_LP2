package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.models.Proveedor;
import com.tienda.repositories.IProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private IProveedorRepository _proveedorRepository;
	
	public List<Proveedor> getAll(){
		return _proveedorRepository.findAll();
	}
}
