package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.filter.PesquisaFilter;
import com.feluma.naa.model.EstudanteContato;
import com.feluma.naa.service.PesquisaService;

public class EstudanteContatoDAO extends GenericoDAO<EstudanteContato, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction transaction;

	protected Class<EstudanteContato> getClasseDaEntidade() {
		return EstudanteContato.class;
	}

	public List<EstudanteContato> listarEstudanteContatos(Long codigoEstudante) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct estcon from EstudanteContato estcon  ");
		sb.append(" where estcon.estudante.codigo = :codigo             ");
		sb.append(" order by estcon.codigo desc                         ");

		return getEntityManager().createQuery(sb.toString(), EstudanteContato.class).setParameter("codigo", codigoEstudante)
				.getResultList();
	}

	public EstudanteContato recuperarEstudanteContato(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct estcon from EstudanteContato estcon ");
		sb.append("where estcon.codigo = :codigo");

		return getEntityManager().createQuery(sb.toString(), EstudanteContato.class).setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<EstudanteContato> pesquisarEstudante(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct estcon from EstudanteContato estcon ");
		sb.append("where estcon.codigo <> 0 ");

		PesquisaService.testarCamposPesquisaEstudanteContato(sb, listaParamentrosPesquisa);

		sb.append("order by est.codigo desc");

		return getEntityManager().createQuery(sb.toString(), EstudanteContato.class).getResultList();
	}

}