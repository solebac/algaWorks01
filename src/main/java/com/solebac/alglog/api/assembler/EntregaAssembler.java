package com.solebac.alglog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.solebac.alglog.api.controller.domain.model.Entrega;
import com.solebac.alglog.api.controller.domain.model.dto.EntregaInput;
import com.solebac.alglog.api.controller.domain.model.dto.EntregaModelDto;

@Component
public class EntregaAssembler {
	private ModelMapper modelMapper;

	public EntregaAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}



	public EntregaModelDto toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModelDto.class);
	}
	
	public List<EntregaModelDto> toCollectionModel(List<Entrega> entregas){
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}
	public Entrega toEntity(EntregaInput input) {
		return modelMapper.map(input, Entrega.class);
	}
}
