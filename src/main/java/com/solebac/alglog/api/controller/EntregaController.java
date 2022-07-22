package com.solebac.alglog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.dto.DestinatarioModelDto;
import com.solebac.alglog.api.controller.domain.model.dto.EntregaModelDto;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;
import com.solebac.alglog.api.controller.domain.services.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService entregaservice;
	
	private EntregaRepository entregaRepository;

	public EntregaController(SolicitacaoEntregaService entregaservice, EntregaRepository entregaRepository) {
		super();
		this.entregaservice = entregaservice;
		this.entregaRepository = entregaRepository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		return entregaservice.solicitar(entrega);
	}
	
	@GetMapping
	public List<Entrega> listar(){
		return entregaRepository.findAll();
	}
	
	/*@GetMapping("/{entregaId}")
	public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}*/
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModelDto> buscar(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega -> {
					EntregaModelDto obj = new EntregaModelDto();
					obj.setId(entrega.getId());
					obj.setNomeCliente(entrega.getCliente().getNome());
					obj.setDestinario(new DestinatarioModelDto());
					obj.getDestinario().setNome(entrega.getDestinatario().getNome());
					obj.getDestinario().setLogradouro(entrega.getDestinatario().getLogradouro());
					obj.getDestinario().setBairro(entrega.getDestinatario().getBairro());
					obj.getDestinario().setNumero(entrega.getDestinatario().getNumero());
					obj.getDestinario().setComplemento(entrega.getDestinatario().getLogradouro());
					obj.setTaxa(entrega.getTaxa());
					obj.setStatus(entrega.getStatus());
					obj.setDataPedido(entrega.getDataPedido());
					obj.setDataFinalizacao(entrega.getDataFinalizacao());
					return ResponseEntity.ok(obj);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
