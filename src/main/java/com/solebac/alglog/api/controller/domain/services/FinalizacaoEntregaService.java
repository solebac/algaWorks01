package com.solebac.alglog.api.controller.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solebac.alglog.api.controller.domain.exception.NegocioException;
import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.StatusEntrega;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;

@Service
public class FinalizacaoEntregaService {

	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		//Regra de negocio.
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
	
}
