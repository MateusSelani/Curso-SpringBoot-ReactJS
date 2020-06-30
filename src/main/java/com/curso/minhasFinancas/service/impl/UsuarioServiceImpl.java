package com.curso.minhasFinancas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.curso.minhasFinancas.model.entity.Usuario;
import com.curso.minhasFinancas.model.repository.UsuarioRepository;
import com.curso.minhasFinancas.service.UsuarioService;
import com.curso.minhasFinancas.service.exceptions.RegraNegocioException;

public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String Email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		// TODO Auto-generated method stub
		boolean existe = repository.existsByEmail(email);
		
		if (existe) {
			throw new RegraNegocioException("Usuário já existe");
		}
	}

}
