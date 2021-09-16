package com.acanel.azul_teste;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.acanel.azul_teste.model.Cliente;
import com.acanel.azul_teste.model.Endereco;
import com.acanel.azul_teste.repository.ClienteRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClienteRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ClienteRepository clienteRepository;
	
	Endereco endereco = new Endereco("Teste logradouro", "25060040", "teste", "teste", "teste", "teste", "teste");

	@Test
	@Rollback(false)
	public void testSaveNewClient() {

		Cliente cliente = new Cliente();

		cliente.setCpf("21111111111");
		cliente.setEmail("teste@teste.com");
		cliente.setEndereco(endereco);

		entityManager.persist(cliente);

		Cliente _cliente = clienteRepository.findOneByCpf("21111111111");
		assertThat(_cliente.getId()).isGreaterThan(0);
	}

	@Test
	public void cientesList() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		Cliente cliente = new Cliente();

		cliente.setCpf("21111111112");
		cliente.setEmail("teste2@teste.com");
		cliente.setEndereco(endereco);

		entityManager.persist(cliente);

		clienteRepository.findAll().forEach(clientes::add);

		assertThat(clientes).size().isGreaterThan(0);
	}

	@Test
	public void findClienteByCPF() {

		Cliente cliente = new Cliente();

		cliente.setCpf("21111111113");
		cliente.setEmail("teste3@teste.com");
		cliente.setEndereco(endereco);

		entityManager.persist(cliente);

		assertThat(clienteRepository.findOneByCpf("21111111113").getEmail()).isEqualTo("teste3@teste.com");
	}

}
