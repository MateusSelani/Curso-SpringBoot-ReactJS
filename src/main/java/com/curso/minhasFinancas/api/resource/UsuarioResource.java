package com.curso.minhasFinancas.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.minhasFinancas.model.entity.Usuario;
import com.curso.minhasFinancas.pai.dto.UsuarioDTO;
import com.curso.minhasFinancas.service.UsuarioService;
import com.curso.minhasFinancas.service.exceptions.RegraNegocioException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

	private UsuarioService service;

	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity salvar(UsuarioDTO dto) {

		Usuario usuario = Usuario.builder().nome(dto.getEmail()).senha(dto.getSenha()).build();

		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);

		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
