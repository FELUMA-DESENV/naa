package com.feluma.naa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.naa.dao.VacinaDAO;
import com.feluma.naa.model.Vacina;

@FacesConverter(forClass=Vacina.class)
public class VacinaConverter implements Converter {

	@Inject
	private VacinaDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Vacina retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.pesquisarPorCodigo(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Vacina vacina = (Vacina) value;
			return vacina.getCodigo() == null ? null : vacina.getCodigo().toString();
		}
		
		return "";
	}

}