package com.feluma.naa.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.model.Vacina;
import com.feluma.naa.service.VacinaService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroVacinaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private VacinaService vacinaService;

	private Vacina vacina;


	public CadastroVacinaBean() {
		vacina = new Vacina();
	}

	@PostConstruct
	public void inicializar() {
		if(!FacesUtil.isPostback()){
			
		}
	}
	
	public boolean isEditando(){
		Boolean editando = false;
		if(vacina.getCodigo() != null){
			editando = true;
			vacina = vacinaService.recuperarVacina(vacina.getCodigo());
		}	
		
		return editando;
	}
	
	public String salvar(){
		String retorno = null;
		
		vacina = vacinaService.salvar(vacina);
		
		FacesUtil.addInfoMessage("Vacina salva com sucesso!");
		
		return retorno;
	}	
	
	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
}