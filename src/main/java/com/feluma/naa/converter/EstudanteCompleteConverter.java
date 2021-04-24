package com.feluma.naa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.naa.dao.EstudanteDAO;
import com.feluma.naa.model.Estudante;

@FacesConverter("EstudanteCompleteConverter")
public class EstudanteCompleteConverter implements Converter{	

		@Inject
		private EstudanteDAO dao;
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Estudante retorno = null;
			
			if (value != null) {
				String nome = value;
				retorno = dao.recuperarEstudante(nome);
			}
			
			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				Estudante estudante = (Estudante) value;
				return estudante.getNome() == null ? null : estudante.getNome();
			}
			
			return "";
		}

}
