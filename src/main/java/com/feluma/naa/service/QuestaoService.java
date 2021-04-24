package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.QuestaoDAO;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.Questao;

public class QuestaoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private QuestaoDAO dao;
		
	public List<Questao> listar() {
		return dao.listar();
	}
	
	public List<Questao> recuperarQuestoes(Estudante estudante) {
		return dao.recuperarQuestoes(estudante);
	}

	public Questao salvar(Questao questao) throws Exception {
		return dao.salvar(questao);
	}
	
	
}
