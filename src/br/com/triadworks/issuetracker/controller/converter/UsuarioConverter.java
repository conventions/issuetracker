package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import com.jsf.conventions.converter.AbstractBaseConverter;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.UsuarioService;

@Advanced
@FacesConverter(value="usuarioConverter",forClass=Usuario.class)
public class UsuarioConverter extends AbstractBaseConverter {
		@Inject
		 public void setService(UsuarioService usuarioService){
			 super.setBaseService(usuarioService);
		 }

}
