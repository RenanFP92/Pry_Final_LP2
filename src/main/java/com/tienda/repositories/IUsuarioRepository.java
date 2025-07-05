package com.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsuarioAndPasswordUsuario(String usuario, String passwordUsuario);
}
