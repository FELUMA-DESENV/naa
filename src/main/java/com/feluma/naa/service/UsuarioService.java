package com.feluma.naa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.omnifaces.util.Utils;

import com.feluma.naa.util.mail.Email;
import com.feluma.naa.util.jms.ProdutorJMS;
import com.feluma.naa.dao.UsuarioDAO;
import com.feluma.naa.filter.UsuarioFilter;
import com.feluma.naa.model.Usuario;
import com.feluma.naa.security.Criptografia;
import com.feluma.naa.util.utilitario.Utilitario;

public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO dao;
	
	@Inject
	private ProdutorJMS produtorJMSService;
	
	public List<Usuario> listar(){
		return dao.listar();
	}
	
	public List<Usuario> listarUsuario() {
		return dao.listarUsuario();
	}
	
	public Usuario pesquisarPorCodigo(Long codigo){
		return dao.pesquisarPorCodigo(codigo);
	}
	
	public List<Usuario> pesquisar(UsuarioFilter filtro){
		return dao.pesquisar(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}
	
	public List<Usuario> pesquisarUsuario(UsuarioFilter filtro) {
		return dao.pesquisarUsuario(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}
	
	public Usuario recuperarUsuario(Long codigo) {
		return dao.recuperarUsuario(codigo);
	}

	public Long verificarEmail(String email) {
		return dao.verificarEmail(email);
	}
	
	public Usuario recuperarUsuarioPorEmail(String email) {
		return dao.recuperarUsuarioPorEmail(email);
	}
	
	public Usuario criptografarSenha(Usuario usuario){
		if(usuario.getCodigo() != null && Utils.isAnyEmpty(usuario.getSenha())){
			usuario.setSenha(dao.pesquisarPorCodigo(usuario.getCodigo()).getSenha());
		} else {
			usuario.setSenha(Criptografia.criptografarSHA1(usuario.getSenha()));
		}
		return usuario;
	}
	
	public Usuario salvar(Usuario usuario){
		try {
			return dao.salvar(criptografarSenha(usuario));
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}
	
	public Usuario excluir(Usuario usuario) {
		try {
			return dao.excluir(usuario.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("O registro não pode ser excluído");
		}
	}

	public String restaurarSenha(Usuario usuario) {
		String novaSenha = Utilitario.gerarStringAleatoria();
		usuario.setSenha(Criptografia.criptografarSHA1(novaSenha));
		
		try {
			dao.restaurarSenha(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return novaSenha;
	}
	
	public void montarMensagemParaEnviarEmailParaRestaurarSenha(Usuario usuario, String senha) {
		StringBuffer sb = new StringBuffer();
		sb.append("Olá !");
		sb.append("<br /><br />Sua nova senha para acesso ao Portal do Candidato é: "); sb.append(senha);
		sb.append("<br />Você pode alterar a senha gerada no Menu Usuários/Meus Dados");
		sb.append("<br /><br />Essa é uma mensagem automática, favor não responder!");
		sb.append("<br /><br />Att. <br />Equipe Informática Feluma");
		
		enviarEmail(usuario, sb.toString());
	}
	
	public void enviarEmail(Usuario usuario, String mensagem){
		Email email = new Email();
		email.setRemetente("sistemas@cienciasmedicasmg.edu.br");
		email.setDestinatario(usuario.getEmail());
		email.setAssunto("Portal do Candidato");
		email.setMensagem(mensagem);
		
		produtorJMSService.produzirMensagem(email);
	}
	
	public boolean compararSenha(Usuario usuario, String senhaInformada) {
		return usuario.getSenha().equals(Criptografia.criptografarSHA1(senhaInformada));
	}
	
}
