package com.curso.minhasFinancas.service.impl;

import org.springframework.stereotype.Service;

import com.curso.minhasFinancas.enums.StatusLancamento;
import com.curso.minhasFinancas.model.entity.Lancamento;
import com.curso.minhasFinancas.model.repository.LancamentoRepository;
import com.curso.minhasFinancas.service.LancamentoServices;

@Service
public class LancamentoServiceImpl implements LancamentoServices {

	private LancamentoRepository lancamentoRepository;

	public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	public Lancamento salvar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lancamento atualizar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

}
