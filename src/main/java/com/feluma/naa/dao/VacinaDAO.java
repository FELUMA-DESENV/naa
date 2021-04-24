package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.filter.PesquisaFilter;
import com.feluma.naa.model.Vacina;

public class VacinaDAO extends GenericoDAO<Vacina, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction transaction;

	protected Class<Vacina> getClasseDaEntidade() {
		return Vacina.class;
	}

	public List<Vacina> listarVacinas() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct vac from Vacina vac  ");
		sb.append(" order by vac.codigo desc            ");

		return getEntityManager().createQuery(sb.toString(), Vacina.class).getResultList();
	}

	public Vacina recuperarVacina(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct vac from Vacina vac ");
		sb.append("where vac.codigo = :codigo          ");

		return getEntityManager().createQuery(sb.toString(), Vacina.class).setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Vacina> pesquisarVacina(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct vac from Vacina vac ");
		sb.append("where vac.codigo <> 0 			   ");

//		PesquisaService.testarCamposPesquisaVacina(sb, listaParamentrosPesquisa);

		sb.append("order by est.codigo desc            ");

		return getEntityManager().createQuery(sb.toString(), Vacina.class).getResultList();
	}

}