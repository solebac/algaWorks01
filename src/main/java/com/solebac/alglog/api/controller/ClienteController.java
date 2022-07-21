package com.solebac.alglog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.controller.domain.model.Cliente;
import com.solebac.alglog.api.controller.domain.model.repositories.ClienteRepository;
import com.solebac.alglog.api.controller.domain.services.CatalagoClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CatalagoClienteService services;
	
	public ClienteController(ClienteRepository clienteRepository, CatalagoClienteService services) {
		//Simulando @Autowired
		this.clienteRepository = clienteRepository;
		this.services = services;
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
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody @Valid Cliente client){
		//return clienteRepository.save(client);
		return services.salvar(client);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody @Valid Cliente cliente){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		//cliente = clienteRepository.save(cliente);
		cliente = services.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}") 
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		//clienteRepository.deleteById(clienteId);
		services.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
	
}
