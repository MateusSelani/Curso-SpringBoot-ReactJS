package com.curso.minhasFinancas.service;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.curso.minhasFinancas.model.entity.Usuario;
import com.curso.minhasFinancas.model.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsurioServiceTest {

	@Autowired
	UsuarioService service;

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	EntityManager entityManager;

	@Test //(expected = Teste.None.class)
	public void validarEmail() {

		// ação
		service.validarEmail("teste@email.com");
		
	}

	@Test //(expected = RegraNegocioException.class)
	public void ErroEmailJaCadastrado() {

		// cenário
		Usuario usuario = Usuario.builder().nome("usuario").email("teste@email.com").build();
		entityManager.persist(usuario);

		// ação
		service.validarEmail("teste@email.com");
	}
}
