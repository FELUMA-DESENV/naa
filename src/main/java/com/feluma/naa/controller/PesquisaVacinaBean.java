package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.filter.EstudanteFilter;
import com.feluma.naa.filter.VacinaFilter;
import com.feluma.naa.model.Vacina;
import com.feluma.naa.service.VacinaService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaVacinaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private VacinaService vacinaService;
	
	private List<Vacina> vacinas;
	private Vacina vacina;
	private VacinaFilter filtro;
	
	
	public PesquisaVacinaBean(){
		setFiltro(new VacinaFilter());
	}
	
	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			vacinas = vacinaService.listarVacinas();
		}
	}
		
	public void excluir(Vacina Estudante){
		vacinaService.excluir(Estudante);
		vacinas.remove(vacina);
		FacesUtil.addInfoMessage("Vacina excluída com sucesso.");
	}

	public VacinaService getVacinaService() {
		return vacinaService;
	}

	public void setVacinaService(VacinaService vacinaService) {
		this.vacinaService = vacinaService;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public VacinaFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(VacinaFilter filtro) {
		this.filtro = filtro;
	}	
}