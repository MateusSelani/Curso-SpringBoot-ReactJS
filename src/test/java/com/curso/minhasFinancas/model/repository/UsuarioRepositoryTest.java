package com.curso.minhasFinancas.model.repository;

import java.util.Optional;

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
		Usuario usuario = criarUsuario();
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

	@Test
	public void devePersistirUsuarioNaBaseDados() {

		// Cenário
		Usuario usuario = criarUsuario();

		// Ação
		Usuario usuarioSalvo = entityManager.persist(usuario);

		// Verificação
		Assertions.assertNotNull(usuarioSalvo.getId());
	}

	@Test
	public void buscaUsuarioPorEmail() {

		// Cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		// Verificação
		boolean result = repository.existsByEmail("teste@email.com");

		// Ação
		Assertions.assertTrue(result);
	}
	
	@Test
	public void retornarVazioSeNaoExistirNaBase() {

		// Verificação
		boolean result = repository.existsByEmail("teste@email.com");

		// Ação
		Assertions.assertFalse(result);
	}

	public static Usuario criarUsuario() {

		return Usuario.builder().nome("usuario").email("teste@email.com").senha("senha").build();
	}
}
