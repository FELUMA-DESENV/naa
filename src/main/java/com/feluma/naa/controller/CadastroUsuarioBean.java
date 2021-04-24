package com.feluma.naa.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.model.Perfil;
import com.feluma.naa.model.StatusAtivoInativo;
import com.feluma.naa.model.Usuario;
import com.feluma.naa.service.PerfilService;
import com.feluma.naa.service.UsuarioService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private PerfilService perfilService;
	
	private Usuario usuario;
	private Perfil perfil;
	private List<Perfil> perfis;
	private List<StatusAtivoInativo> statusUsuarios;
	
	public CadastroUsuarioBean() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<StatusAtivoInativo> getStatusUsuarios() {
		return statusUsuarios;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	@PostConstruct
	public void inicializar() {
		if(!FacesUtil.isPostback()){
			statusUsuarios = Arrays.asList(StatusAtivoInativo.values());
			perfis = perfilService.listar();
			
		}
	}
	
	public String salvar(String pagina){
		String retorno = null;
		
		if(usuario.getCodigo() == null) {
			usuario.setStatusUsuario(StatusAtivoInativo.ATIVO);
			if(pagina.equals("pesquisaUsuario")) {
				usuario.getPerfis().add(perfilService.pesquisarPorCodigo(1L));
			} else {
				usuario.getPerfis().add(perfilService.pesquisarPorCodigo(2L));
			}
		}
		
		usuario = usuarioService.salvar(usuario);
		
		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
		retorno = pagina + ".xhtml?faces-redirect=true";
		
		return retorno;
	}
	
	public boolean isEditando(){
		Boolean editando = false;
		if(usuario.getCodigo() != null){
			editando = true;
			usuario = usuarioService.recuperarUsuario(usuario.getCodigo());
		}	
		return editando;
	}
	
	public void adicionarPerfil(){
		if(perfil != null){
			if(usuario.getPerfis().contains(perfil)){
				FacesUtil.addAlertMessage("O perfil selecionado já se encontra adicionado!");
			} else {
				usuario.getPerfis().add(perfil);
				perfil = new Perfil();
			}
		} else {
			FacesUtil.addAlertMessage("Favor selecionar um perfil!");
		}
	}
	
	public void excluirPerfil(Perfil perfil){		
		usuario.getPerfis().remove(perfil);
	}
	
}