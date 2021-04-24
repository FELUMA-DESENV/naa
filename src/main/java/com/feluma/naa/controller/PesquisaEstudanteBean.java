package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.filter.EstudanteFilter;
import com.feluma.naa.model.Curso;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.service.CursoService;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEstudanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstudanteService estudanteService;

	@Inject
	private CursoService cursoService;
	
	private List<Estudante> estudantes;
	private Estudante estudante;
	private EstudanteFilter filtro;
	
	private List<Curso> listaCursos;

	public PesquisaEstudanteBean(){
		filtro = new EstudanteFilter();
	}
	
	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}
	
	public EstudanteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(EstudanteFilter filtro) {
		this.filtro = filtro;
	}
	
	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}


	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			estudantes = estudanteService.listarEstudante();
			listaCursos = cursoService.listar();
		}
	}
	
	public void pesquisar(){
		estudantes = estudanteService.pesquisar(filtro);
	}
	
	
//	public void buscarEstudantePorId(Long id) {
//		
//		estudante = estudanteService.pesquisarPorCodigo(id);
//				
//	}
	
	public void excluir(Estudante Estudante){
		estudanteService.excluir(Estudante);
		estudantes.remove(Estudante);
		FacesUtil.addInfoMessage("Estudante excluído com sucesso.");
	}

	
}