package com.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.models.Boleta;

public interface IBoletaRepository extends JpaRepository<Boleta, Integer> {
	List<Boleta> findAllByOrderByNumBoletaDesc();
}
