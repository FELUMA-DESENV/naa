package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.naa.dao.AtendimentoDAO;
import com.feluma.naa.model.Atendimento;

public class AtendimentoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AtendimentoDAO dao;
		
	public List<Atendimento> listar(){
		return dao.listar();
	}
	
	public List<Atendimento> listarAtendimento() {
		return dao.listarAtendimentos();
	}
	
	public Atendimento pesquisarPorCodigo(Long codigo){
		return dao.pesquisarPorCodigo(codigo);
	}
	
	public Atendimento recuperarAtendimento(Long codigo) {
		return dao.recuperarAtendimento(codigo);
	}
	
	public Atendimento salvar(Atendimento atendimento){
		try {
			return dao.salvar(atendimento);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}
	
	public Atendimento excluir(Atendimento atendimento) {
		try {
			return dao.excluir(atendimento.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("O registro não pode ser excluído");
		}
	}

	public void remover(Atendimento atendimento) {
		try {
			dao.excluir(atendimento.getCodigo());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	public List<Atendimento> listarAtendimentosEstudante(Long codigoEstudante) {
		return dao.listarAtendimentosEstudante(codigoEstudante);
	}

}
