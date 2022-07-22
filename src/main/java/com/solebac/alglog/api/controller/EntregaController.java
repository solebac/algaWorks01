package com.solebac.alglog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.assembler.EntregaAssembler;
import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.dto.EntregaModelDto;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;
import com.solebac.alglog.api.controller.domain.services.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService entregaservice;
	
	private EntregaRepository entregaRepository;
	
	private EntregaAssembler entregaAssembler;

	public EntregaController(SolicitacaoEntregaService entregaservice, EntregaRepository entregaRepository,
			EntregaAssembler entregaAssembler) {
		super();
		this.entregaservice = entregaservice;
		this.entregaRepository = entregaRepository;
		this.entregaAssembler = entregaAssembler;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModelDto solicitar(@Valid @RequestBody Entrega entrega) {
		return entregaAssembler.toModel(entregaservice.solicitar(entrega));
	}
	
	@GetMapping
	public List<EntregaModelDto> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
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
					EntregaModelDto obj = entregaAssembler.toModel(entrega);
					return ResponseEntity.ok(obj);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
