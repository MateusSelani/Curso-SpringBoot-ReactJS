package com.curso.minhasFinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.minhasFinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);
	
}
