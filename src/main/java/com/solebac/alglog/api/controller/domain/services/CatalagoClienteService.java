package com.solebac.alglog.api.controller.domain.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.solebac.alglog.api.controller.domain.exception.NegocioException;
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
		boolean emailEmUso = clienteRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(clientExist -> clientExist.equals(client));
		if (emailEmUso) {
			throw new NegocioException("Já existe cliente cadastrado com esse email.");
		}
		return clienteRepository.save(client);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
