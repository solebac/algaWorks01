package com.solebac.alglog.api.controller.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solebac.alglog.api.controller.domain.exception.NegocioException;
import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.Ocorrencia;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;

@Service
public class RegistroOcorrenciaService {
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = entregaRepository.findById(entregaId)
				.orElseThrow(() -> new NegocioException("Entrega not Found."));
		return entrega.adicionarOcorrencia(descricao);
	}
}
