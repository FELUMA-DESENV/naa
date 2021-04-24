package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.EstudanteVacinaDAO;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.VacinaEstudante;

public class EstudanteVacinaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstudanteVacinaDAO dao;
		
	public List<VacinaEstudante> listar(){
		return dao.listar();
	}
	
	public List<VacinaEstudante> listarVacinaEstudantes(Long codigoEstudante) {
		return dao.listarVacinaEstudantes(codigoEstudante);
	}
	
	public VacinaEstudante pesquisarPorCodigo(Long codigo){
		return dao.pesquisarPorCodigo(codigo);
	}
	
//	public List<VacinaEstudante> pesquisar(VacinaEstudanteFilter filtro){
//		return dao.pesquisar(PesquisaService.carregaListaParametrosPesquisa(filtro));
//	}
//	
//	public List<Estudante> pesquisarEstudante(EstudanteFilter filtro) {
//		return dao.pesquisarEstudante(PesquisaService.carregaListaParametrosPesquisa(filtro));
//	}
//	
	public VacinaEstudante recuperarVacinaEstudante(Long codigo) {
		return dao.recuperarVacinaEstudante(codigo);
	}

	
	public VacinaEstudante salvar(VacinaEstudante VacinaEstudante){
		try {
			return dao.salvar(VacinaEstudante);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}
	
	
	public VacinaEstudante excluir(VacinaEstudante VacinaEstudante) {
		try {
			return dao.excluir(VacinaEstudante.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("O registro não pode ser excluído");
		}
	}

	public void remover(VacinaEstudante VacinaEstudante) {
		try {
			dao.excluir(VacinaEstudante.getCodigo());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
}
