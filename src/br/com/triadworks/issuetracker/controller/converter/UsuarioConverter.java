package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.triadworks.issuetracker.dao.UsuarioService;
import br.com.triadworks.issuetracker.model.Usuario;

@FacesConverter(forClass=Usuario.class)
@Advanced
public class UsuarioConverter implements Converter {

	@Inject
	private UsuarioService dao;
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		if (value == null)
			return null;
		
		Long id = new Long(value);
		
		Usuario usuario = dao.carrega(id);
		return usuario;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		if (value == null)
			return null;
		
		Usuario usuario = (Usuario) value;
		return usuario.getId().toString();
	}

}
