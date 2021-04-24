package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.PerfilDAO;
import com.feluma.naa.model.Perfil;

public class PerfilService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PerfilDAO dao;
	
	public List<Perfil> listar(){
		return dao.listar();
	}

	public Perfil pesquisarPorCodigo(Long codigo) {
		return dao.pesquisarPorCodigo(codigo);
	}

	public Perfil pesquisarPorNome(String nome) {
		return dao.pesquisarPorNome(nome);
	}

}
