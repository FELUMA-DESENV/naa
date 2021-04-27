package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.filter.PesquisaFilter;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.service.PesquisaService;

public class EstudanteDAO extends GenericoDAO<Estudante, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserTransaction transaction;
	
	protected Class<Estudante> getClasseDaEntidade() {
		return Estudante.class;
	}
	
	
	public List<Estudante> listarEstudante() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct est from Estudante est ");		
		sb.append("order by est.codigo desc");
		
		return getEntityManager().createQuery(sb.toString(), Estudante.class).getResultList();
	}

	public Estudante recuperarEstudante(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select est from Estudante est                     ");
		sb.append("              left join fetch est.estudanteContatos estCon ");
		sb.append("              left join fetch est.atendimentos         ate ");
		sb.append("where est.codigo = :codigo                                 ");
		
		return getEntityManager().createQuery(sb.toString(), Estudante.class)
				.setParameter("codigo", codigo).getSingleResult();
	}
	
	public List<Estudante> pesquisarEstudante(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct est from Estudante est ");
		sb.append("where est.codigo <> 0 ");
		
		PesquisaService.testarCamposPesquisaEstudante(sb, listaParamentrosPesquisa);
		
		sb.append("order by est.codigo desc");
		
		return getEntityManager().createQuery(sb.toString(), Estudante.class).getResultList();
	}

	public List<Estudante> recuperarEstudantesComVacinasVencidas() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct est from Estudante est                        ");
		sb.append("              join fetch est.vacinaEstudantes vacEst     	 ");
		sb.append("where vacEst.dataValidade <= CURRENT_TIMESTAMP				 ");
		sb.append("order by vacEst.dataValidade");
		
		return getEntityManager().createQuery(sb.toString(), Estudante.class).getResultList();			
	}

	public Estudante pesquisaEstudantePorCPF(String cpf) {
		StringBuffer sb = new StringBuffer();
		Estudante estudante = new Estudante();

		sb.append("select distinct est from Estudante est                         ");
		sb.append("              left join fetch est.estudanteContatos estCon     ");
		sb.append("              left join fetch est.estudanteInformacoes estInfo ");
		sb.append("where est.cpf = :cpf                                    	 	  ");
		
		try {
			estudante =  getEntityManager().createQuery(sb.toString(), Estudante.class)
					    .setParameter("cpf", cpf).getSingleResult();
		} catch (Exception e) {
		}
		
		return estudante;
	}
	
	public List<Estudante> pesquisaEstudantePorNome(String nome) {
		StringBuffer sb = new StringBuffer();
		List<Estudante> estudante = new ArrayList<Estudante>();

		sb.append("select distinct est from Estudante est  		");
		sb.append("where est.nome LIKE :nome order by est.nome  ");
		
		try {
			estudante =  getEntityManager().createQuery(sb.toString(), Estudante.class)
					    .setParameter("nome", "%" + nome + "%").getResultList();
		} catch (Exception e) {
		}
		
		return estudante;
	}

	public Estudante listarEstudanteVacinas(Long codigoEstudante) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct est from Estudante est                       ");
		sb.append("              left join fetch est.vacinaEstudantes vacEst    ");
		sb.append("  where est.codigo = :codigoEstudante                        ");
		sb.append("order by vacEst.dataValidade									");
		
		return getEntityManager().createQuery(sb.toString(), Estudante.class)
				 .setParameter("codigoEstudante", codigoEstudante).getSingleResult();	
	}


	public List<Estudante> listarEstudantesComVacinasObrigatoriasPendentes() {
		StringBuffer sb = new StringBuffer();
		sb.append("select cod_est, nom_est, mat_est, cpf_est from EstudantesComVacinasPendentes");
		
		Query query = getEntityManager().createNativeQuery(sb.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> lista = query.getResultList();
		
		List<Estudante> valores = new ArrayList<Estudante>();
		
		for(Object[] linha : lista){
			Estudante estudante = new Estudante();
			estudante.setCodigo(Long.parseLong(linha[0].toString()));
			estudante.setNome(linha[1].toString());
			estudante.setMatricula(linha[2].toString());
			estudante.setCpf(linha[3].toString());
			
			valores.add(estudante);
		}
		
		return valores;
	}


	public Estudante recuperarEstudante(String nome) {
		StringBuffer sb = new StringBuffer();
		sb.append("select est from Estudante est                      ");
		sb.append("      left join fetch est.estudanteContatos estCon ");
		sb.append("where est.nome = :nome                             ");
		
		return getEntityManager().createQuery(sb.toString(), Estudante.class)
				.setParameter("nome", nome).getSingleResult();
	}
	
}