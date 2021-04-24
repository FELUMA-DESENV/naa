package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.EstudanteContato;
import com.feluma.naa.service.EstudanteContatoService;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EstudanteContatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstudanteContatoService estudanteContatoService;
	
	@Inject
	private EstudanteService estudanteService;
	
	private EstudanteContato estudanteContato;
	private Estudante estudante;

	private List<EstudanteContato> estudanteContatos;
	


	@PostConstruct
	public void inicializar() {
		if (!FacesUtil.isPostback()) {
			estudanteContato = new EstudanteContato();
			//estudante = estudanteService.recuperarEstudante(estudante.getCodigo());
		}
	}

		
	public void salvar(){			
		
		estudanteContato.setEstudante(estudante);		
		estudante.addEstudanteContato(estudanteContato);		
		estudante = estudanteService.salvar(estudante);
		limpar();
		FacesUtil.addInfoMessage("Contato do estudante salvo com sucesso!");
		
	}
	
	
	private void limpar() {
		estudanteContato = new EstudanteContato();
		
	}


	public void excluir(EstudanteContato estudanteContato){
		estudanteContatoService.remover(estudanteContato);
		estudante.getEstudanteContatos().remove(estudanteContato);
		FacesUtil.addInfoMessage("Estudante excluído com sucesso.");
	}

	public EstudanteContato getEstudanteContato() {
		return estudanteContato;
	}

	public void setEstudanteContato(EstudanteContato estudanteContato) {
		this.estudanteContato = estudanteContato;
	}
	
	public List<EstudanteContato> getEstudanteContatos() {
		return estudanteContatos;
	}

	public void setEstudanteContatos(List<EstudanteContato> estudanteContatos) {
		this.estudanteContatos = estudanteContatos;
	}
	
	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public void limparCadastro() {
		estudanteContato = new EstudanteContato();
	}
}