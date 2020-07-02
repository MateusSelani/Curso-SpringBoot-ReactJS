package com.curso.minhasFinancas.model.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.curso.minhasFinancas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void verificarExistenciaEmail() {

		// Cenário
		Usuario usuario = Usuario.builder().nome("usuario").email("teste@email.com").build();
		entityManager.persist(usuario);

		// Ação // Execução
		boolean result = repository.existsByEmail("teste@email.com");

		// Verificação
		Assertions.assertEquals(true, result);
	}

	@Test
	public void retornarFalsoUsuarioSemEmail() {

		// Ação
		boolean result = repository.existsByEmail("qualquerEmail@email.com");

		// Verificação
		Assertions.assertEquals(false, result);
	}
}
