package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.EstudanteInformacao;
import com.feluma.naa.model.Questao;
import com.feluma.naa.model.TipoSanguineo;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.service.QuestaoService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EstudanteInformacaoFreeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstudanteService estudanteService;
	@Inject
	private QuestaoService questaoService;
	
	private Estudante estudante;
	private Questao questao;
	private EstudanteInformacao estudanteInformacao;
	
	private List<TipoSanguineo> listaTiposSanguineos;
	private List<Questao> questoes;


	public EstudanteInformacaoFreeBean() {
		estudante = new Estudante();
	}
	
	public void inicializar() {
		if(!FacesUtil.isPostback()){
			estudante = estudanteService.recuperarEstudante(estudante.getCodigo());
			questoes = questaoService.recuperarQuestoes(estudante);
		}
	}
	
	public void  salvar(){
		
		for (Questao questao : questoes) {
			try {
				questao = questaoService.salvar(questao);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		PrimeFaces.current().executeScript("PF('dlg').show();");
	}	
	
	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public EstudanteInformacao getEstudanteInformacao() {
		return estudanteInformacao;
	}

	public void setEstudanteInformacao(EstudanteInformacao estudanteInformacao) {
		this.estudanteInformacao = estudanteInformacao;
	}

	public List<TipoSanguineo> getListaTiposSanguineos() {
		return listaTiposSanguineos;
	}

	public void setListaTiposSanguineos(List<TipoSanguineo> listaTiposSanguineos) {
		this.listaTiposSanguineos = listaTiposSanguineos;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	
}