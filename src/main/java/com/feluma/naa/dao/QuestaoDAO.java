package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.EstudanteInformacao;
import com.feluma.naa.model.Questao;

public class QuestaoDAO extends GenericoDAO<Questao, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Questao> getClasseDaEntidade() {
		return Questao.class;
	}
	
	public List<Questao> recuperarQuestoes(Estudante estudante) {
		List<Questao> retorno = new ArrayList<Questao>();
		
		StringBuffer sbCompleta = new StringBuffer();
		sbCompleta.append("select distinct que from Questao que                                                               		");
		sbCompleta.append("               left join fetch que.estudanteInformacoes estInf where (estInf.estudante.codigo = :codigo) ");
		
		retorno =  getEntityManager().createQuery(sbCompleta.toString(), Questao.class).setParameter("codigo", estudante.getCodigo()).getResultList();
		
		if(retorno.size() == 0) {		
			StringBuffer sbVazia = new StringBuffer();
			sbVazia.append("select distinct que from Questao que ");
			retorno =  getEntityManager().createQuery(sbVazia.toString(), Questao.class).getResultList();
			for (Questao questao : retorno) {
				questao.setEstudanteInformacoes(new ArrayList<EstudanteInformacao>());
				questao.getEstudanteInformacoes().add(new EstudanteInformacao(estudante,questao));				
			}
		}	
		
		return retorno;
	}
}

