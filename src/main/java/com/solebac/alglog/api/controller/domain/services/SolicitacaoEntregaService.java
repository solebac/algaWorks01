package com.solebac.alglog.api.controller.domain.services;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.solebac.alglog.api.controller.domain.model.Cliente;
import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.StatusEntrega;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	private EntregaRepository entregaRepository;
	private CatalagoClienteService catalagoClienteService;

	public SolicitacaoEntregaService(EntregaRepository entregaRepository,
			CatalagoClienteService catalagoClienteService) {
		this.entregaRepository = entregaRepository;
		this.catalagoClienteService = catalagoClienteService;
	}

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = catalagoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		//Implements regra de horario
		return entregaRepository.save(entrega);
	}
	
	
}
