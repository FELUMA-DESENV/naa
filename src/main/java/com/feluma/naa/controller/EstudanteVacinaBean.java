package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.Vacina;
import com.feluma.naa.model.VacinaEstudante;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.service.EstudanteVacinaService;
import com.feluma.naa.service.VacinaService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EstudanteVacinaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VacinaService vacinaService;

	@Inject
	private EstudanteService estudanteService;

	@Inject
	private EstudanteVacinaService estudanteVacinaService;

	private VacinaEstudante estudanteVacina = new VacinaEstudante();
	private Estudante estudante;
	private Vacina vacina;

	private List<VacinaEstudante> estudanteVacinas;
	private List<Vacina> vacinas;

	public void inicializar() {
		if (!FacesUtil.isPostback()) {
			estudanteVacina = new VacinaEstudante();
			vacinas = vacinaService.listar();
			estudante = estudanteService.listarEstudanteVacinas(estudante.getCodigo());
			limparVacinas();
		}
	}

	private void limparVacinas() {
		List<Vacina> vacinasAux = new ArrayList<Vacina>();
		for (VacinaEstudante vacinaEstudante : estudante.getVacinaEstudantes()) {
			vacinasAux.add(vacinaEstudante.getVacina());
		}

		vacinas.removeAll(vacinasAux);
	}

	public void salvar() {
		estudanteVacina.setEstudante(estudante);
		estudante.getVacinaEstudantes().add(estudanteVacina);
		estudante = estudanteService.salvar(estudante);
		limpar();
		limparVacinas();
		FacesUtil.addInfoMessage("Vacina do estudante salva com sucesso!");		
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	private void limpar() {
		estudanteVacina = new VacinaEstudante();
	}

	public void excluir(VacinaEstudante estudanteVacina) {
		vacinas.add(estudanteVacina.getVacina());
		estudanteVacinaService.remover(estudanteVacina);
		estudante.getVacinaEstudantes().remove(estudanteVacina);
		FacesUtil.addInfoMessage("Estudante excluído com sucesso.");
	}

	public VacinaEstudante getEstudanteVacina() {
		return estudanteVacina;
	}

	public void setEstudanteVacina(VacinaEstudante estudanteVacina) {
		this.estudanteVacina = estudanteVacina;
	}

	public List<VacinaEstudante> getEstudanteVacinas() {
		return estudanteVacinas;
	}

	public void setEstudanteVacinas(List<VacinaEstudante> estudanteVacinas) {
		this.estudanteVacinas = estudanteVacinas;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public void limparCadastro() {
		limpar();
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
}