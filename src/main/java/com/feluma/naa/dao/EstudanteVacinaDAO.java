package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.filter.PesquisaFilter;
import com.feluma.naa.model.VacinaEstudante;
import com.feluma.naa.service.PesquisaService;

public class EstudanteVacinaDAO extends GenericoDAO<VacinaEstudante, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction transaction;

	protected Class<VacinaEstudante> getClasseDaEntidade() {
		return VacinaEstudante.class;
	}

	public List<VacinaEstudante> listarVacinaEstudantes(Long codigoEstudante) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct estvac from VacinaEstudante estvac   ");
		sb.append(" where estvac.estudante.codigo = :codigo             ");
		sb.append(" order by estvac.codigo desc                         ");

		return getEntityManager().createQuery(sb.toString(), VacinaEstudante.class).setParameter("codigo", codigoEstudante)
				.getResultList();
	}

	public VacinaEstudante recuperarVacinaEstudante(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct estcon from EstudanteContato estcon ");
		sb.append("where estcon.codigo = :codigo");

		return getEntityManager().createQuery(sb.toString(), VacinaEstudante.class).setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<VacinaEstudante> pesquisarEstudante(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct estcon from  estcon ");
		sb.append("where estcon.codigo <> 0 ");

		PesquisaService.testarCamposPesquisaEstudanteContato(sb, listaParamentrosPesquisa);

		sb.append("order by est.codigo desc");

		return getEntityManager().createQuery(sb.toString(), VacinaEstudante.class).getResultList();
	}

}