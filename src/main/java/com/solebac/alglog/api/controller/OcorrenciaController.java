package com.solebac.alglog.api.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.assembler.OcorrenciaAssembler;
import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.Ocorrencia;
import com.solebac.alglog.api.controller.domain.model.dto.OcorrenciaModel;
import com.solebac.alglog.api.controller.domain.model.dto.input.OcorrenciaInput;
import com.solebac.alglog.api.controller.domain.services.BuscaEntregaService;
import com.solebac.alglog.api.controller.domain.services.RegistroOcorrenciaService;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private BuscaEntregaService buscaService;
	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssembler assembler;

	public OcorrenciaController(BuscaEntregaService buscaService, RegistroOcorrenciaService registroOcorrenciaService,
			OcorrenciaAssembler assembler) {
		super();
		this.buscaService = buscaService;
		this.registroOcorrenciaService = registroOcorrenciaService;
		this.assembler = assembler;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId,
				ocorrenciaInput.getDescricao());
		return assembler.toModel(ocorrenciaRegistrada);
	}
	
	@GetMapping
	public Set<OcorrenciaModel> listar(@PathVariable Long entregaId){
		Entrega entrega = buscaService.buscar(entregaId);
		return assembler.toCollectionModel(entrega.getOcorrencias());
	}

}
