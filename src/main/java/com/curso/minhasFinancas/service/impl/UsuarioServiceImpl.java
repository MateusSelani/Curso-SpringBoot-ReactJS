package com.curso.minhasFinancas.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.curso.minhasFinancas.model.entity.Usuario;
import com.curso.minhasFinancas.model.repository.UsuarioRepository;
import com.curso.minhasFinancas.service.UsuarioService;
import com.curso.minhasFinancas.service.exceptions.ErroAutenticacao;
import com.curso.minhasFinancas.service.exceptions.RegraNegocioException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
//	@Autowired
	private UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado");
		}
		if (usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Inválida");
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
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
