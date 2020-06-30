package com.curso.minhasFinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.curso.minhasFinancas.model.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Test
	private void deveVerificarExistenciaEmail() {

		// Cenário
		Usuario usuario = Usuario.builder().nome("usuario").email("teste@email.com").build();
		repository.save(usuario);

		// Ação
		boolean result = repository.existsByEmail("teste@email.com");

		// Verificação
		Assertions.assertThat(result).isTrue();
	}
}
