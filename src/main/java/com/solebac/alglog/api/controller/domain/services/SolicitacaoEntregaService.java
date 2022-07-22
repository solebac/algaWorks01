package com.solebac.alglog.api.controller.domain.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.StatusEntrega;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	private EntregaRepository entregaRepository;

	public SolicitacaoEntregaService(EntregaRepository entregaRepository) {
		this.entregaRepository = entregaRepository;
	}
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		//Implements regra de horario
		return entregaRepository.save(entrega);
	}
	
	
}
