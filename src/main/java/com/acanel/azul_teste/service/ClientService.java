package com.acanel.azul_teste.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.acanel.azul_teste.model.Cliente;
import com.acanel.azul_teste.model.Endereco;
import com.acanel.azul_teste.repository.ClienteRepository;

@Service
@Transactional
public class ClientService {

	@Autowired
	ClienteRepository clienteRepository;

	private static final Logger log = LoggerFactory.getLogger(ClientService.class);

	public ResponseEntity<Endereco> getEnderecoByCEP(String cep) {

		cep = cep.replaceAll("-", "");

		if (cep.length() != 8) {
			log.error("Erro: CEP inválido - deve conter 8 dígitos: " + cep);
			throw new IllegalArgumentException("CEP inválido - deve conter 8 dígitos: " + cep);
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		RestTemplate restTemplate = new RestTemplate();
		Endereco endereco = restTemplate.getForObject("http://viacep.com.br/ws/" + cep + "/json/", Endereco.class,
				params);

		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);

	}

	public Cliente createCliente(Cliente cliente) {
		try {
			return clienteRepository.save(cliente);
		} catch (Exception e) {
			log.error("Erro: " + e.getMessage() + " / Cause: " + e.getCause());
			return null;
		}
	}

	public List<Cliente> findAllClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		clienteRepository.findAll().forEach(clientes::add);
		;

		log.debug("clientes", clientes);
		return clientes;

	}

	public Cliente findByEmail(String email) {

		return clienteRepository.findOneByEmail(email);

	}

}
