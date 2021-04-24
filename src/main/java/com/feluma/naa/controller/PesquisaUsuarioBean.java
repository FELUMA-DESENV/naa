package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.filter.EstudanteFilter;
import com.feluma.naa.filter.UsuarioFilter;
import com.feluma.naa.model.Estudante;
import com.feluma.naa.model.Usuario;
import com.feluma.naa.service.EstudanteService;
import com.feluma.naa.service.UsuarioService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private EstudanteService estudanteService;
	
	private List<Usuario> usuarios;
	private Usuario usuario;
	private UsuarioFilter filtro;
	private List<Estudante> estudantes;
	private List<Estudante> estudantesComVacinasObrigatoriasPendentes;

	@PostConstruct
	public void inicializar() {
		if (!FacesUtil.isPostback()) {
			usuarios = usuarioService.listarUsuario();
			this.estudantesComVacinasVencidas();
			this.estudantesComVacinasObrigatoriasPendentes();
		}
	}

	public void pesquisar() {
		usuarios = usuarioService.pesquisar(filtro);
	}

	public void excluir(Usuario usuario) {
		usuarioService.excluir(usuario);
		usuarios.remove(usuario);
		FacesUtil.addInfoMessage("Usuário excluído com sucesso.");
	}
	
	public void estudantesComVacinasVencidas() {
		estudantes = estudanteService.estudantesComVacinasVencidas();
	}
	
	public void estudantesComVacinasObrigatoriasPendentes() {
		setEstudantesComVacinasObrigatoriasPendentes(estudanteService.estudantesComVacinasObrigatoriasPendentes());
	}

	public PesquisaUsuarioBean() {
		filtro = new UsuarioFilter();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UsuarioFilter filtro) {
		this.filtro = filtro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

	public List<Estudante> getEstudantesComVacinasObrigatoriasPendentes() {
		return estudantesComVacinasObrigatoriasPendentes;
	}

	public void setEstudantesComVacinasObrigatoriasPendentes(List<Estudante> estudantesComVacinasObrigatoriasPendentes) {
		this.estudantesComVacinasObrigatoriasPendentes = estudantesComVacinasObrigatoriasPendentes;
	}

}