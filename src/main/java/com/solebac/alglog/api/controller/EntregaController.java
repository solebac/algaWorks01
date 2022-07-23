package com.solebac.alglog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.assembler.EntregaAssembler;
import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.dto.EntregaInput;
import com.solebac.alglog.api.controller.domain.model.dto.EntregaModelDto;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;
import com.solebac.alglog.api.controller.domain.services.FinalizacaoEntregaService;
import com.solebac.alglog.api.controller.domain.services.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService entregaservice;

	private EntregaRepository entregaRepository;

	private EntregaAssembler entregaAssembler;

	private FinalizacaoEntregaService finalizacao;

	public EntregaController(SolicitacaoEntregaService entregaservice, EntregaRepository entregaRepository,
			EntregaAssembler entregaAssembler, FinalizacaoEntregaService finalizacao) {
		super();
		this.entregaservice = entregaservice;
		this.entregaRepository = entregaRepository;
		this.entregaAssembler = entregaAssembler;
		this.finalizacao = finalizacao;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModelDto solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		// A classe de dominio não fica sabendo nada da classe da API
		Entrega newEntrega = entregaAssembler.toEntity(entregaInput);
		return entregaAssembler.toModel(entregaservice.solicitar(newEntrega));
	}

	@GetMapping
	public List<EntregaModelDto> listar() {
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}

	/*
	 * @GetMapping("/{entregaId}") public ResponseEntity<Entrega>
	 * buscar(@PathVariable Long entregaId){ return
	 * entregaRepository.findById(entregaId) .map(ResponseEntity::ok)
	 * .orElse(ResponseEntity.notFound().build()); }
	 */
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModelDto> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId).map(entrega -> {
			EntregaModelDto obj = entregaAssembler.toModel(entrega);
			return ResponseEntity.ok(obj);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacao.finalizar(entregaId);
	}
}
