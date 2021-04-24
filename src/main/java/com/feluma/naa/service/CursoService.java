package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.CursoDAO;
import com.feluma.naa.model.Curso;

public class CursoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CursoDAO dao;
		
	public List<Curso> listar() {
		return dao.listar();
	}
}
