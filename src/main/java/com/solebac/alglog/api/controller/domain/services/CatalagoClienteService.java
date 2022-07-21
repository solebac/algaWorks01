package com.solebac.alglog.api.controller.domain.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.solebac.alglog.api.controller.domain.model.Cliente;
import com.solebac.alglog.api.controller.domain.model.repositories.ClienteRepository;

@Service
public class CatalagoClienteService {

	private ClienteRepository clienteRepository;

	public CatalagoClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Transactional
	public Cliente salvar(Cliente client) {
		return clienteRepository.save(client);
	}
	
}
