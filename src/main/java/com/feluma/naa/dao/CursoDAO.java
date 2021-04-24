package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.List;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.model.Curso;
import com.feluma.naa.model.Estudante;

public class CursoDAO extends GenericoDAO<Curso, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Curso> getClasseDaEntidade() {
		return Curso.class;
	}
}

