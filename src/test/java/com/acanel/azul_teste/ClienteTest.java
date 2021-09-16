package com.acanel.azul_teste;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.acanel.azul_teste.model.Cliente;
import com.acanel.azul_teste.model.Endereco;

public class ClienteTest {

	Cliente cliente = new Cliente();
	Endereco endereco = new Endereco("Teste logradouro", "25060040", "teste", "teste", "teste", "teste", "teste");

	@Test
	public void testSetGetFields() {

		cliente.setCpf("11111111115");
		cliente.setEmail("teste5@teste.com");
		cliente.setNome("teste");
		cliente.setId(50000000l);

		cliente.setEndereco(endereco);

		assertThat(cliente.getCpf()).isNotNull();
		assertThat(cliente.getEmail()).isNotNull();
		assertThat(cliente.getEndereco()).isNotNull();
		assertThat(cliente.getId()).isNotNull();
		assertThat(cliente.getNome()).isNotNull();

		assertThat(endereco.getBairro()).isNotNull();
		assertThat(endereco.getCep()).isNotNull();
		assertThat(endereco.getComplemento()).isNotNull();
		assertThat(endereco.getIbge()).isNotNull();
		assertThat(endereco.getLocalidade()).isNotNull();
		assertThat(endereco.getLogradouro()).isNotNull();
		assertThat(endereco.getUf()).isNotNull();
		assertThat(endereco.getId());
	}

	@Test
	public void testToString() {
		assertThat(cliente.toString()).isNotNull();
		assertThat(endereco.toString()).isNotNull();
	}

	@Test
	public void testContructor() {
		assertThat(new Cliente("33333333333", "teste33@teste", "teste", endereco)).isNotNull();
	}
}
