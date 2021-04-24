package com.feluma.naa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.naa.dao.EstudanteContatoDAO;
import com.feluma.naa.model.EstudanteContato;

@FacesConverter(forClass = EstudanteContato.class)
public class EstudanteContatoConverter implements Converter {

	@Inject
	private EstudanteContatoDAO dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EstudanteContato retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = dao.pesquisarPorCodigo(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			EstudanteContato estudanteContato = (EstudanteContato) value;
			return estudanteContato.getCodigo() == null ? null : estudanteContato.getCodigo().toString();
		}

		return "";
	}
}