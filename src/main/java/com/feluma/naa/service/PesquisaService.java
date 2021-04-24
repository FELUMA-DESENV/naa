package com.feluma.naa.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.feluma.naa.filter.PesquisaFilter;

public class PesquisaService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static List<PesquisaFilter> carregaListaParametrosPesquisa(Object filtro){
		List<PesquisaFilter> listaParametrosPesquisa = new ArrayList<PesquisaFilter>();
		if(filtro != null){
			String operacao = null;
			Object valor = null;
			Field[] campos = filtro.getClass().getDeclaredFields();
			
			for(int i=1; i<campos.length; i++){  
				campos[i].setAccessible(true);
				try {
					valor = campos[i].get(filtro);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				if(valor != null && !valor.equals("")){
					if(valor instanceof String) operacao = "like";
					else operacao = "=";
					listaParametrosPesquisa.add(new PesquisaFilter(campos[i].getName(), valor, operacao));
				}
			}
		}
		return listaParametrosPesquisa;
	}
	
	public static StringBuffer testarCamposPesquisaUsuario(StringBuffer sb, List<PesquisaFilter> listaParamentrosPesquisa) {
		for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
			if(objPesquisa.getCampo().equals("nome")){
				sb.append(" and usu.nome like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
			if(objPesquisa.getCampo().equals("email")){
				sb.append(" and usu.email like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
		}
		return sb;
	}
	
	public static StringBuffer testarCamposPesquisaEstudante(StringBuffer sb, List<PesquisaFilter> listaParamentrosPesquisa) {
		for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
			if(objPesquisa.getCampo().equals("nome")){
				sb.append(" and usu.nome like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
			if(objPesquisa.getCampo().equals("email")){
				sb.append(" and usu.email like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
		}
		return sb;
	}
	
	public static StringBuffer testarCamposPesquisaEstudanteContato(StringBuffer sb, List<PesquisaFilter> listaParamentrosPesquisa) {
		for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
			if(objPesquisa.getCampo().equals("nome")){
				sb.append(" and usu.nome like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
			if(objPesquisa.getCampo().equals("email")){
				sb.append(" and usu.email like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
		}
		return sb;
	}
	
	public static StringBuffer testarCamposPesquisaCurso(StringBuffer sb, List<PesquisaFilter> listaParamentrosPesquisa) {
		for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
			if(objPesquisa.getCampo().equals("descricao")){
				sb.append(" and cur.descricao like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
		}
		
		return sb;
	}
	
}