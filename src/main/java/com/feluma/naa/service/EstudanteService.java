package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.EstudanteDAO;
import com.feluma.naa.filter.EstudanteFilter;
import com.feluma.naa.model.Estudante;

public class EstudanteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstudanteDAO dao;
		
	public List<Estudante> listar(){
		return dao.listar();
	}
	
	public List<Estudante> listarEstudante() {
		return dao.listarEstudante();
	}
	
	public Estudante pesquisarPorCodigo(Long codigo){
		return dao.pesquisarPorCodigo(codigo);
	}
	
	public List<Estudante> pesquisar(EstudanteFilter filtro){
		return dao.pesquisar(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}
	
	public List<Estudante> pesquisarEstudante(EstudanteFilter filtro) {
		return dao.pesquisarEstudante(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}
	
	public Estudante recuperarEstudante(Long codigo) {
		return dao.recuperarEstudante(codigo);
	}
	
	public List<Estudante> estudantesComVacinasVencidas() {
		
		return dao.recuperarEstudantesComVacinasVencidas();
	}
	
	public Estudante salvar(Estudante estudante){
		try {
			return dao.salvar(estudante);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}
	
	public Estudante excluir(Estudante estudante) {
		try {
			return dao.excluir(estudante.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("O registro não pode ser excluído");
		}
	}

	public Estudante pesquisaEstudantePorCPF(String cpf) {
		return dao.pesquisaEstudantePorCPF(cpf);
	}
	
	public List<Estudante> pesquisaEstudantePorNome(String nome) {
		return dao.pesquisaEstudantePorNome(nome);
	}

	public Estudante listarEstudanteVacinas(Long codigoEstudante) {
		return dao.listarEstudanteVacinas(codigoEstudante);
	}

	public List<Estudante> estudantesComVacinasObrigatoriasPendentes() {
		return dao.listarEstudantesComVacinasObrigatoriasPendentes();
	}

}
