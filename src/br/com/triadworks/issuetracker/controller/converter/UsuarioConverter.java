package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;
import org.conventionsframework.converter.AbstractBaseConverter;
import org.conventionsframework.qualifier.Service;
import org.conventionsframework.service.BaseService;

import br.com.triadworks.issuetracker.model.Usuario;

@Advanced
@FacesConverter(value="usuarioConverter",forClass=Usuario.class)
public class UsuarioConverter extends AbstractBaseConverter {
		 @Inject
		 public void setService(@Service(entity=Usuario.class) BaseService usuarioService){
			 super.setBaseService(usuarioService);
		 }

}
