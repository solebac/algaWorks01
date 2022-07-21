package com.solebac.alglog.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.controller.domain.model.Cliente;
import com.solebac.alglog.api.controller.domain.model.repositories.ClienteRepository;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	
	public ClienteController(ClienteRepository clienteRepository) {
		//Simulando @Autowired
		this.clienteRepository = clienteRepository;
	}

	@GetMapping
	public List<Cliente> lista() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> obj = clienteRepository.findById(clienteId);
		//return obj.orElse(null); sempre retorno 200ok|Isso é ruim
		if (obj.isPresent()) {
			return ResponseEntity.ok(obj.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
