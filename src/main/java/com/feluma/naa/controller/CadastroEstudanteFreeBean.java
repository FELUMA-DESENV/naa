package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.feluma.naa.model.Curso;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.TipoSanguineo;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstudanteFreeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstudanteService estudanteService;

	private Estudante estudante;

	private List<TipoSanguineo> listaTiposSanguineos;

	private List<Curso> listaCursos;

	private TipoSanguineo tipoSanguineo;

	public CadastroEstudanteFreeBean() {
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
		if (!FacesUtil.isPostback()) {
			listaTiposSanguineos = Arrays.asList(TipoSanguineo.values());
		}
	}

	public void pesquisaEstudantePorCPF() {
		String cpf = estudante.getCpf();
		estudante = estudanteService.pesquisaEstudantePorCPF(cpf);

		if (estudante.getCodigo() == null) {
			FacesUtil.addErroMessage("CPF inválido. Por favor entre em contato com a instituição!");
		}  
		else if (!estudante.getEstudanteInformacoes().isEmpty()) {
			FacesUtil.addAlertMessage("Estudante já foi atualizado! Entre em contato com Departamento Acadêmico para realizar as alterações necessárias.");	
			estudante = new Estudante();
		}
		else {
			PrimeFaces.current().executeScript("PF('dlg').hide();");
		}
	}

	public String salvar() {
		String retorno = null;
		estudante = estudanteService.salvar(estudante);
		
		retorno = "estudanteContatoFree.xhtml?faces-redirect=true&estudante=" + estudante.getCodigo();

		return retorno;
	}

}