package com.solebac.alglog.api.controller.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Destinatario {

	@Column(name = "destinatario_nome")
	private String nome;
	@Column(name = "destinatario_logradouro")
	private String logradouro;
	@Column(name = "destinatario_numero")
	private String numero;
	@Column(name = "destinatario_bairro")
	private String bairro;
	@Column(name = "destinatario_complemento")
	private String complemento;
	
	public Destinatario(String nome, String logradouro, String numero, String bairro, String complemento) {
		super();
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
	}
	public Destinatario() {
		super();
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
