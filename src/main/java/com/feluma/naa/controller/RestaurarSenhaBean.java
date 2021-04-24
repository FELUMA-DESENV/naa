package com.feluma.naa.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.naa.model.Usuario;
import com.feluma.naa.service.UsuarioService;
import com.feluma.naa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RestaurarSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private String email;

	public RestaurarSenhaBean() {
		limpar();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void limpar() {
		this.email = null;
	}

	public void restaurar() {
		StringBuffer sb = new StringBuffer();
		Usuario usuario = usuarioService.recuperarUsuarioPorEmail(email);

		if (usuario != null) {
			String senha = usuarioService.restaurarSenha(usuario);
			usuarioService.montarMensagemParaEnviarEmailParaRestaurarSenha(usuario, senha);
			sb.append("Uma nova senha foi gerada e enviada para o e-mail " + usuario.getEmail());
			FacesUtil.addInfoMessage(sb.toString());
		} else {
			sb.append("Não foi encontrado nenhum usuário com o cpf informado!");
			FacesUtil.addErroMessage(sb.toString());
		}
		
		limpar();
	}

}