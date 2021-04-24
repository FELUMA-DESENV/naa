package com.feluma.naa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.naa.dao.EstudanteDAO;
import com.feluma.naa.model.Estudante;

@FacesConverter(forClass=Estudante.class)
public class EstudanteConverter implements Converter {

	@Inject
	private EstudanteDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estudante retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.recuperarEstudante(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Estudante estudante = (Estudante) value;
			return estudante.getCodigo() == null ? null : estudante.getCodigo().toString();
		}
		
		return "";
	}

}