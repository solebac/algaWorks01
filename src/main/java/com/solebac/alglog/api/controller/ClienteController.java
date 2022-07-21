package com.solebac.alglog.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)//.map(client -> ResponseEntity.ok(client))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Cliente adicionar(@RequestBody Cliente client){
		return clienteRepository.save(client);
	}
}
