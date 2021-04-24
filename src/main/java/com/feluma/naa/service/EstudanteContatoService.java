package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.EstudanteContatoDAO;
import com.feluma.naa.filter.EstudanteContatoFilter;
import com.feluma.naa.model.EstudanteContato;

public class EstudanteContatoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstudanteContatoDAO dao;
		
	public List<EstudanteContato> listar(){
		return dao.listar();
	}
	
	public List<EstudanteContato> listarEstudanteContatos(Long codigoEstudante) {
		return dao.listarEstudanteContatos(codigoEstudante);
	}
	
	public EstudanteContato pesquisarPorCodigo(Long codigo){
		return dao.pesquisarPorCodigo(codigo);
	}
	
	public List<EstudanteContato> pesquisar(EstudanteContatoFilter filtro){
		return dao.pesquisar(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}
	
//	public List<Estudante> pesquisarEstudante(EstudanteFilter filtro) {
//		return dao.pesquisarEstudante(PesquisaService.carregaListaParametrosPesquisa(filtro));
//	}
//	
	public EstudanteContato recuperarEstudanteContato(Long codigo) {
		return dao.recuperarEstudanteContato(codigo);
	}

	
	public EstudanteContato salvar(EstudanteContato estudanteContato){
		try {
			return dao.salvar(estudanteContato);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}
	
	
	public EstudanteContato excluir(EstudanteContato estudanteContato) {
		try {
			return dao.excluir(estudanteContato.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("O registro não pode ser excluído");
		}
	}

	public void remover(EstudanteContato estudanteContato) {
		try {
			dao.excluir(estudanteContato.getCodigo());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}


}
