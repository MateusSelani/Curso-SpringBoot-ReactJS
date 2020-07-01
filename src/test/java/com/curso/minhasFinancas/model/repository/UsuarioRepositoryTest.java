package com.curso.minhasFinancas.model.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.curso.minhasFinancas.model.entity.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Test
	private void deveVerificarExistenciaEmail() {

		// Cenário
		Usuario usuario = Usuario.builder().nome("usuario").email("teste@email.com").build();
		repository.save(usuario);

		// Ação // Execução
		boolean result = repository.existsByEmail("teste@email.com");

		// Verificação
		// Assertions.assertThat(result).isTrue();
		Assertions.assertEquals(true, result);
	}
	
	@Test
	private void deveRetornarFalsoUsuarioSemEmail() {
		// Cenario
		repository.deleteAll();
		
		//Ação
		boolean result = repository.existsByEmail("teste@email.com");
		
		//Verificação
		Assertions.assertEquals(false, result);
	}
}
