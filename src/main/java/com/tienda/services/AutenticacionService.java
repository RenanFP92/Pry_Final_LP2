package com.tienda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dtos.AutenticacionFilter;
import com.tienda.models.Usuario;
import com.tienda.repositories.IUsuarioRepository;

@Service
public class AutenticacionService {

	@Autowired
	private IUsuarioRepository _usuarioRepository;
	
	public Usuario autenticacion(AutenticacionFilter filter) {
		return _usuarioRepository.findByUsuarioAndPasswordUsuario(filter.getUsuario(), filter.getPasswordUsuario());
	}
}
