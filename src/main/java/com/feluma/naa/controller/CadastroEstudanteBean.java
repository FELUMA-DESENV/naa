package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.filter.EstudanteFilter;
import com.feluma.naa.model.Curso;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.TipoSanguineo;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstudanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstudanteService estudanteService;
	
	private Estudante estudante;
		
	private List<TipoSanguineo> listaTiposSanguineos;
	
	private List<Curso> listaCursos;
	
	private TipoSanguineo tipoSanguineo;
	
	

	public CadastroEstudanteBean() {
		estudante = new Estudante();
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}
	
	public List<TipoSanguineo> getListaTiposSanguineos() {
		return listaTiposSanguineos;
	}

	public void setListaTiposSanguineos(List<TipoSanguineo> listaTiposSanguineos) {
		this.listaTiposSanguineos = listaTiposSanguineos;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	@PostConstruct
	public void inicializar() {
		if(!FacesUtil.isPostback()){
			listaTiposSanguineos = Arrays.asList(TipoSanguineo.values());
		}
	}
	
	public boolean isEditando(){
		Boolean editando = false;
		if(estudante.getCodigo() != null){
			editando = true;
			estudante = estudanteService.recuperarEstudante(estudante.getCodigo());


		}	
		return editando;
	}
	
	public String salvar(){
		String retorno = null;
		
		estudante = estudanteService.salvar(estudante);
		
		FacesUtil.addInfoMessage("Estudante salvo com sucesso!");
		
		return retorno;
	}	
	
}