package com.feluma.naa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.naa.dao.AtendimentoDAO;
import com.feluma.naa.model.Atendimento;
import com.feluma.naa.model.Vacina;

@FacesConverter(forClass=Atendimento.class)
public class AtendimentoConverter implements Converter {

	@Inject
	private AtendimentoDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Atendimento retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.pesquisarPorCodigo(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Atendimento atendimento = (Atendimento) value;
			return atendimento.getCodigo() == null ? null : atendimento.getCodigo().toString();
		}
		
		return "";
	}

}