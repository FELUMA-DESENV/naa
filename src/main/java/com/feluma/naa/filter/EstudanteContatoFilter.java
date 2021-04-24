package com.feluma.naa.filter;

import java.io.Serializable;


public class EstudanteContatoFilter implements Serializable {

	private static final long serialVersionUID = 1L;


	private String celular;
	private String nome;
	private String parentensco;
	private String telefone;
	
	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getParentensco() {
		return parentensco;
	}
	public void setParentensco(String parentensco) {
		this.parentensco = parentensco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}