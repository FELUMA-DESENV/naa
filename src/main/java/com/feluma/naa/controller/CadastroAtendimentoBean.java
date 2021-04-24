package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.feluma.naa.model.Atendimento;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.security.Seguranca;
import com.feluma.naa.service.AtendimentoService;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AtendimentoService atendimentoService;

	@Inject
	private EstudanteService estudanteService;

	@Inject
	private Seguranca seguranca;
	
	private Atendimento atendimento;

	private Estudante inputAutoCompleteEstudante;
	
	private List<Estudante> estudantes;
	
	
	
	public CadastroAtendimentoBean() {
		atendimento = new Atendimento();
	}

	@PostConstruct
	public void inicializar() {
		if(!FacesUtil.isPostback()){
			atendimento.setDataAtendimento(new Date());
			atendimento.setUsuario(seguranca.getUsuario());
		}
	}
	
	public boolean isEditando(){
		Boolean editando = false;
		if(atendimento.getCodigo() != null){
			editando = true;
			atendimento = atendimentoService.recuperarAtendimento(atendimento.getCodigo());
			inputAutoCompleteEstudante = atendimento.getEstudante();
		}	
		
		return editando;
	}
	
	public String salvar(){
		String retorno = null;
		
		atendimento = atendimentoService.salvar(atendimento);
		
		FacesUtil.addInfoMessage("Atendimento salva com sucesso!");
		
		return retorno;
	}	
	
	public List<Estudante> autoCompleteEstudantes(String nome) {
       
        estudantes = estudanteService.pesquisaEstudantePorNome(nome);
         
        return estudantes;
    }
	
	public void setEstudanteSelected(SelectEvent event) {
		atendimento.setEstudante((Estudante) event.getObject());
		
	}
	
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
	public Estudante getInputAutoCompleteEstudante() {
		return inputAutoCompleteEstudante;
	}

	public void setInputAutoCompleteEstudante(Estudante inputAutoCompleteEstudante) {
		this.inputAutoCompleteEstudante = inputAutoCompleteEstudante;
	}

}