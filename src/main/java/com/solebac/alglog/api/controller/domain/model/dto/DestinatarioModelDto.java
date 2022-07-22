package com.solebac.alglog.api.controller.domain.model.dto;

import javax.persistence.Embeddable;

public class DestinatarioModelDto {

	private String nome;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	
	public DestinatarioModelDto() {
		super();
	}
	public DestinatarioModelDto(String nome, String logradouro, String numero, String bairro, String complemento) {
		super();
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
}
