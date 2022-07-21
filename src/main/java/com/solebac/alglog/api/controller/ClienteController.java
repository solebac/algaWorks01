package com.solebac.alglog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.controller.domain.model.Cliente;
import com.solebac.alglog.api.controller.domain.model.repositories.ClienteRepository;

@RestController
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	
	public ClienteController(ClienteRepository clienteRepository) {
		//Simulando @Autowired
		this.clienteRepository = clienteRepository;
	}



	@GetMapping(value = "/clientes")
	public List<Cliente> lista() {
		//Codigo JPQL
		return clienteRepository.findAll();
	}
}
