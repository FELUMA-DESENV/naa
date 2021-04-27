package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.filter.PesquisaFilter;
import com.feluma.naa.model.Atendimento;

public class AtendimentoDAO extends GenericoDAO<Atendimento, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction transaction;

	protected Class<Atendimento> getClasseDaEntidade() {
		return Atendimento.class;
	}

	public List<Atendimento> listarAtendimentos() {
		StringBuffer sb = new StringBuffer();
		sb.append("select ate from Atendimento ate  		 		  ");
		sb.append("              join fetch ate.estudante est   	  ");
		sb.append(" order by ate.codigo desc            			  ");

		return getEntityManager().createQuery(sb.toString(), Atendimento.class).getResultList();
	}

	public Atendimento recuperarAtendimento(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select          ate from Atendimento ate 	");
		sb.append("              join fetch ate.estudante est   ");
		sb.append("where ate.codigo = :codigo         			");

		return getEntityManager().createQuery(sb.toString(), Atendimento.class).setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Atendimento> pesquisarAtendimento(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct ate from Atendimento ate ");
		sb.append("where ate.codigo <> 0 			   		");
		sb.append("order by ate.codigo desc            		");

		return getEntityManager().createQuery(sb.toString(), Atendimento.class).getResultList();
	}
	
	public List<Atendimento> listarAtendimentosEstudante(Long codigoEstudante) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ate from Atendimento ate ");
		sb.append("left join fetch ate.estudante est        ");
		sb.append("where est.codigo = :codigoEstudante		");
		sb.append("order by ate.codigo desc            		");

		return getEntityManager().createQuery(sb.toString(), Atendimento.class).setParameter("codigoEstudante", codigoEstudante).getResultList();
	}

	
	
}