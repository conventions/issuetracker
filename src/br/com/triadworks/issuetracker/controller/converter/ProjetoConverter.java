package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.triadworks.issuetracker.dao.ProjetoService;
import br.com.triadworks.issuetracker.model.Projeto;

@FacesConverter(forClass=Projeto.class)
@Advanced
public class ProjetoConverter implements Converter {

	@Inject
	private ProjetoService dao;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		if (value == null)
			return null;
		
		Long id = new Long(value);
		
		
		Projeto projeto = dao.carrega(id);
		return projeto;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		if (value == null)
			return null;
		
		Projeto projeto = (Projeto) value;
		return projeto.getId().toString();
	}

}
