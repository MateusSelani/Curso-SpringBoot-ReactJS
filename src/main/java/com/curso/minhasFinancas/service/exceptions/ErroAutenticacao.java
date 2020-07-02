package com.curso.minhasFinancas.service.exceptions;

public class ErroAutenticacao extends RuntimeException {

	public ErroAutenticacao(String mensagem) {
		super(mensagem);
	}
}
