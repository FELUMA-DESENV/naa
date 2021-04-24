package com.feluma.naa.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.feluma.naa.dao.UsuarioDAO;
import com.feluma.naa.model.Perfil;
import com.feluma.naa.model.Usuario;
import com.feluma.naa.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UsuarioDAO usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarioDAO.logar(login);
		
		UsuarioSistema user = null;
		
		if (usuario != null) {
			user = new UsuarioSistema(usuario, getPerfis(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getPerfis(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Perfil perfil : usuario.getPerfis()) {
			authorities.add(new SimpleGrantedAuthority(perfil.getNome().toUpperCase()));
		}
		
		return authorities;
	}

}