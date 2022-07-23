package com.solebac.alglog.api.controller.domain.model.dto;

import java.time.OffsetDateTime;

public class OcorrenciaModel {

	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
	
	public OcorrenciaModel() {
		super();
	}
	public OcorrenciaModel(Long id, String descricao, OffsetDateTime dataRegistro) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataRegistro = dataRegistro;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public OffsetDateTime getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(OffsetDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	
	
}
