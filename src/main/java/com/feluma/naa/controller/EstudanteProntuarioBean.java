package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.model.Atendimento;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.service.AtendimentoService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EstudanteProntuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtendimentoService atendimentoService;

	private List<Atendimento> atendimentos;

	private Estudante estudante;

	public EstudanteProntuarioBean() {
		estudante = new Estudante();
	}
	
	@PostConstruct
	public void inicializar() {
		if (!FacesUtil.isPostback()) {
			atendimentos = atendimentoService.listarAtendimentosEstudante(estudante.getCodigo());
		}
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

}