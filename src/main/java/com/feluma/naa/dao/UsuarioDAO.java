package com.feluma.naa.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.feluma.naa.dao.generic.GenericoDAO;
import com.feluma.naa.filter.PesquisaFilter;
import com.feluma.naa.model.StatusAtivoInativo;
import com.feluma.naa.model.Usuario;
import com.feluma.naa.service.PesquisaService;

public class UsuarioDAO extends GenericoDAO<Usuario, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserTransaction transaction;
	
	protected Class<Usuario> getClasseDaEntidade() {
		return Usuario.class;
	}
	
	public Usuario logar(String email) {
		Usuario usuario = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("where usu.email like :email ");
		sb.append("and usu.statusUsuario = :statusUsuario");
		
		try {
			usuario = getEntityManager().createQuery(sb.toString(), Usuario.class)
					.setParameter("email", ""+email+"%")
					.setParameter("statusUsuario", StatusAtivoInativo.ATIVO)
					.getSingleResult();
		} catch (Exception e) {}
		
		return usuario;
	}
	
	public List<Usuario> listarUsuario() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("order by usu.codigo desc");
		
		return getEntityManager().createQuery(sb.toString(), Usuario.class)
				.getResultList();
	}

	public Usuario recuperarUsuario(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("where usu.codigo = :codigo");
		
		return getEntityManager().createQuery(sb.toString(), Usuario.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}
	
	public Long verificarEmail(String email) {
		return getEntityManager().createQuery("select count(u) from Usuario u where u.email = :email", Long.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	public void restaurarSenha(Usuario usuario) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update usuario set sen_usu = :senha where cod_usu = :codigoUsuario");
		
		try {
			transaction.begin();
			Query query = getEntityManager().createNativeQuery(sb.toString())
					.setParameter("senha", usuario.getSenha())
					.setParameter("codigoUsuario", usuario.getCodigo());
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception();
		}
	}

	public Usuario recuperarUsuarioPorEmail(String email) {
		StringBuffer sb = new StringBuffer();
		sb.append("select usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("where usu.email = :email");
		
		Usuario usuario = null;
		
		try {
			usuario = getEntityManager().createQuery(sb.toString(), Usuario.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {}
		
		return usuario;
	}

	public List<Usuario> pesquisarUsuario(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("where usu.codigo <> 0 ");
		
		PesquisaService.testarCamposPesquisaUsuario(sb, listaParamentrosPesquisa);
		
		sb.append("order by usu.codigo desc");
		
		return getEntityManager().createQuery(sb.toString(), Usuario.class)
				.getResultList();
	}

}