package com.solebac.alglog.api.controller.domain.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.solebac.alglog.api.controller.domain.exception.NegocioException;
import com.solebac.alglog.api.controller.domain.model.Cliente;
import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.StatusEntrega;
import com.solebac.alglog.api.controller.domain.model.repositories.ClienteRepository;
import com.solebac.alglog.api.controller.domain.model.repositories.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	private EntregaRepository entregaRepository;
	private ClienteRepository clienteRepository;

	public SolicitacaoEntregaService(EntregaRepository entregaRepository, ClienteRepository clienteRepository) {
		super();
		this.entregaRepository = entregaRepository;
		this.clienteRepository = clienteRepository;
	}



	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = clienteRepository.findById(entrega.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente not found."));
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		//Implements regra de horario
		return entregaRepository.save(entrega);
	}
	
	
}
