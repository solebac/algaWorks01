package com.solebac.alglog.api.controller.domain.model.dto.input;

import javax.validation.constraints.NotBlank;

public class OcorrenciaInput {

	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
