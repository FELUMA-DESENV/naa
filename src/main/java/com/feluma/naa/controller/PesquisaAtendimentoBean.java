package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.filter.AtendimentoFilter;
import com.feluma.naa.filter.VacinaFilter;
import com.feluma.naa.model.Atendimento;
import com.feluma.naa.model.Vacina;
import com.feluma.naa.service.AtendimentoService;
import com.feluma.naa.service.VacinaService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AtendimentoService atendimentoService;
	
	private List<Atendimento> atendimentos;
	private Atendimento atendimento;
	
	
	public PesquisaAtendimentoBean(){
	}
	
	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			atendimentos = atendimentoService.listarAtendimento();
		}
	}
		
	public void excluir(Atendimento atendimento){
		atendimentoService.excluir(atendimento);
		atendimentos.remove(atendimento);
		FacesUtil.addInfoMessage("Atendimento excluído com sucesso.");
	}

	public AtendimentoService getAtendimentoService() {
		return atendimentoService;
	}

	public void setAtendimentoService(AtendimentoService atendimentoService) {
		this.atendimentoService = atendimentoService;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}


}