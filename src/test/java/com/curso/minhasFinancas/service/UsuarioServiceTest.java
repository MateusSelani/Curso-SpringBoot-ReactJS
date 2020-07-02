package com.curso.minhasFinancas.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.curso.minhasFinancas.model.entity.Usuario;
import com.curso.minhasFinancas.model.repository.UsuarioRepository;
import com.curso.minhasFinancas.service.exceptions.RegraNegocioException;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioServiceTest {

	@SpyBean
	UsuarioService service;

	@MockBean
	UsuarioRepository repository;

	@Test
	public void deveSalvarUsuario() {

		// Cenário
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());

		Usuario usuario = Usuario.builder().id(1l).nome("nome").email("email").senha("senha").build();

		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

		// Ação
		Usuario usuarioSalvo = service.salvarUsuario(usuario);

		// Verificação
		Assertions.assertNotNull(usuarioSalvo);

		Assertions.assertEquals(1, usuarioSalvo.getId());
		Assertions.assertEquals("nome", usuario.getNome());
		Assertions.assertEquals("teste@email.com", usuario.getEmail());
		Assertions.assertEquals("senha", usuario.getSenha());
	}

	@Test
	public void autenticar() {

		// Cenário
		String email = "teste@email.com";
		String senha = "senha";

		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();

		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

		// Ação
		service.autenticar(email, senha);

		// Verificação
		Assertions.assertNotNull(usuario);
	}

	@Test
	public void naoSalvaUsuarioComEmailCadastrado() {

		// Cenário
		String email = "teste@email.com";
		
		Usuario usuario = Usuario.builder().email("teste@email.com").build();
		
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);
		
		// Ação
		service.salvarUsuario(usuario);
		
		// Verificação
		Mockito.verify(repository, Mockito.never()).save(usuario);
		
	}

	@Test
	public void lancarErroUsuarioNaoEncontrado() {

		// Cenário
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		// Ação
		service.autenticar("teste@email.com", "senha");
	}

	@Test
	public void lancaErroQuandoSenhaNaoBater() {

		// Cenário
		String senha = "senha";

		Usuario usuario = Usuario.builder().email("teste@email.com").senha(senha).build();

		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

		// Ação
		Assertions.assertThrows(null, (Executable) service.autenticar("qualquer@email.com", "123"));
		// service.autenticar("qualquer@email.com", "123");
	}

	@Test // (expected = Teste.None.class)
	public void validarEmail() {

		// Cenário
		// service.validarEmail("teste@email.com");
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

		// Ação
		service.validarEmail("teste@email.com");
	}

	@Test // (expected = RegraNegocioException.class)
	public void ErroEmailJaCadastrado() {

		// cenário
		// Usuario usuario =
		// Usuario.builder().nome("usuario").email("teste@email.com").build();
		// entityManager.persist(usuario);

		// cenário
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

		// ação
		service.validarEmail("teste@email.com");
	}
}
