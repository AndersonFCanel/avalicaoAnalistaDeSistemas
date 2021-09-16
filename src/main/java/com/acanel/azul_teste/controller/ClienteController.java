package com.acanel.azul_teste.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acanel.azul_teste.model.Cliente;
import com.acanel.azul_teste.service.ClientService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	// @Autowired
	// ClienteRepository clienteRepository;

	@Autowired
	ClientService clienteService;

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();

			clientes = clienteService.findAllClientes();

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Erro: " + e.getMessage() + " / Cause: " + e.getCause());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clientes/{cpf}")
	public ResponseEntity<Object> getClientelByCPF(@PathVariable("cpf") String cpf) {
		if (cpf.length() == 11) {
			try {

				Cliente clienteData = clienteService.findByCPF(cpf);
				return new ResponseEntity<>(clienteData, HttpStatus.OK);

			} catch (Exception e) {
				log.error("Erro: " + e.getMessage() + " / Cause: " + e.getCause());
				// return new ResponseEntity<>(HttpStatus.NOT_FOUND );
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Erro: " + e.getMessage() + " / Cause: " + e.getCause());
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF Invalido: " + cpf);

	}

	@PostMapping("/clientes")
	public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente) {
		try {

			cliente.setEndereco(clienteService.getEnderecoByCEP(cliente.getEndereco().getCep()).getBody());

			clienteService.createCliente(cliente);

			return ResponseEntity.status(HttpStatus.CREATED).body(cliente.toString() + " criado com sucesso!");

		} catch (Exception e) {
			log.error("Erro: " + e.getMessage() + " / Cause: " + e.getCause());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro: " + e.getMessage() + " / Cause: " + e.getCause());

		}
	}

}
