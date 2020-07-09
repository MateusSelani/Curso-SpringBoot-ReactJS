package com.curso.minhasFinancas.service;

import com.curso.minhasFinancas.model.entity.Lancamento;

import antlr.collections.List;

public interface LancamentoServices {
	
	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void Lancamento deletar(Lancamento lancamento);
	
	List<Lancamento> salvar(Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
}
