package com.solebac.alglog.api.controller.domain.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Embedded;

import com.solebac.alglog.api.controller.domain.model.StatusEntrega;

public class EntregaModelDto {
	private Long id;
	private String nomeCliente;
	private DestinatarioModelDto destinario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public DestinatarioModelDto getDestinario() {
		return destinario;
	}
	public void setDestinario(DestinatarioModelDto destinario) {
		this.destinario = destinario;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public StatusEntrega getStatus() {
		return status;
	}
	public void setStatus(StatusEntrega status) {
		this.status = status;
	}
	public OffsetDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(OffsetDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	
	
}
