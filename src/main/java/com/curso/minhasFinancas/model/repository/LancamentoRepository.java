package com.curso.minhasFinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.minhasFinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	
}
