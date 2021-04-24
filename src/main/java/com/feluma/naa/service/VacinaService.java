package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.VacinaDAO;
import com.feluma.naa.model.Vacina;

public class VacinaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private VacinaDAO dao;
		
	public List<Vacina> listar(){
		return dao.listar();
	}
	
	public List<Vacina> listarVacinas() {
		return dao.listarVacinas();
	}
	
	public Vacina pesquisarPorCodigo(Long codigo){
		return dao.pesquisarPorCodigo(codigo);
	}
	
//	public List<Vacina> pesquisar(VacinaFilter filtro){
//		return dao.pesquisar(PesquisaService.carregaListaParametrosPesquisa(filtro));
//	}

//	public List<Estudante> pesquisarEstudante(EstudanteFilter filtro) {
//		return dao.pesquisarEstudante(PesquisaService.carregaListaParametrosPesquisa(filtro));
//	}
//	
	public Vacina recuperarVacina(Long codigo) {
		return dao.recuperarVacina(codigo);
	}
	
	public Vacina salvar(Vacina vacina){
		try {
			return dao.salvar(vacina);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}
	
	public Vacina excluir(Vacina vacina) {
		try {
			return dao.excluir(vacina.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("O registro não pode ser excluído");
		}
	}

	public void remover(Vacina vacina) {
		try {
			dao.excluir(vacina.getCodigo());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}


}
