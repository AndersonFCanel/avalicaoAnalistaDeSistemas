package com.acanel.azul_teste;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.acanel.azul_teste.controller.ClienteController;
import com.acanel.azul_teste.model.Cliente;
import com.acanel.azul_teste.model.Endereco;
import com.acanel.azul_teste.repository.ClienteRepository;
import com.acanel.azul_teste.service.ClientService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ClientService clienteService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {
		Application.main(new String[] {});
	}

	@Test
	public void autowiredClientServiceTest() {
		assertThat(ClientService.class);
	}

	@Test
	public void autowiredClienteControllerTest() {
		assertThat(ClienteController.class);
	}

	@DisplayName("Test ClientCEPService valid")
	@Test
	void testGetEnderecoByCEPValid() {
		assertThat(clienteService.getEnderecoByCEP("25060040")).isExactlyInstanceOf(ResponseEntity.class);
	}

	@Test
	void testGetEnderecoByCEPInvalid() {

		assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				clienteService.getEnderecoByCEP("250600");
			}
		});
	}

	@Test
	void testCreateCliente() {
		Endereco endereco = new Endereco("Teste logradouro", "25060040", "teste", "teste", "teste", "teste", "teste");

		assertThat(clienteService.createCliente(new Cliente("11111111111", "teste32@teste", "teste", endereco)));
		assertThrows(Exception.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				clienteService.createCliente(new Cliente("11111111111", "teste32@teste", "teste", endereco));
			}

		});

	}

	@Test
	void testFindAllClientes() {

//		List<Cliente> listClientes = new ArrayList<Cliente>();
//		Endereco endereco = new Endereco("Teste logradouro", "25060040", "teste", "teste", "teste", "teste", "teste");
//
//		listClientes.add(new Cliente("31111111156", "teste156teste.com", "teste", endereco));
//		listClientes.add(new Cliente("41111111157", "teste517@teste.com", "teste", endereco));
//		listClientes.add(new Cliente("51111111158", "teste518@teste.com", "teste", endereco));

		assertThat(clienteService.findAllClientes());

	}

	@Test
	void testFindClientesByEmail() {

		Endereco endereco = new Endereco("Teste logradouro", "25060040", "teste", "teste", "teste", "teste", "teste");
		clienteService.createCliente(new Cliente("11111131111", "teste3233@teste", "teste", endereco));

		assertThat(clienteService.findByEmail("teste3233@teste"));

	}

	@Test
	void testGetALL() {
		URI targetUrl = UriComponentsBuilder.fromUriString("/api/clientes").build().encode().toUri();
		assertThat(this.restTemplate.getForObject(targetUrl, Object.class));
	}

	@Test
	void testGetByEmail() {

		Endereco endereco = new Endereco("Teste logradouro", "25060040", "teste", "teste", "teste", "teste", "teste");
		clienteService.createCliente(new Cliente("12345678901", "teste3s233@teste", "teste", endereco));

		URI targetUrl = UriComponentsBuilder.fromUriString("/api/clientes").queryParam("email", "teste3s233@teste").build()
				.encode().toUri();
		assertThat(this.restTemplate.getForObject(targetUrl, Object.class));
	}

}
