package com.curso.minhasFinancas.service;

import com.curso.minhasFinancas.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String Email, String senha);

	Usuario salvarUsuario(Usuario usuario);

	void validarEmail(String email);

}
