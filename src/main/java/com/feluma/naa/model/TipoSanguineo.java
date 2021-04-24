package com.feluma.naa.model;

public enum TipoSanguineo {
	
	APOSITIVO("A+"),	
	ANEGATIVO("A-"),	
	BPOSITIVO("B+"),	
	BNEGATIVO("B-"),	
	ABPOSITIVO("AB+"),	
	ABNEGATIVO("AB-"),
	OPOSITIVO("O+"),	
	ONAGATIVO("O-"),
	NAOINFORMADO("Não informado");
	
	private String descricao;
	
	private TipoSanguineo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
