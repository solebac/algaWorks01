package com.solebac.alglog.api.assembler;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solebac.alglog.api.controller.domain.model.Ocorrencia;
import com.solebac.alglog.api.controller.domain.model.dto.OcorrenciaModel;

@Component
public class OcorrenciaAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	}
	
	public Set<OcorrenciaModel> toCollectionModel(Set<Ocorrencia> lista){
		return lista.stream()
				.map(this::toModel)
				.collect(Collectors.toSet());
	}
	
}
