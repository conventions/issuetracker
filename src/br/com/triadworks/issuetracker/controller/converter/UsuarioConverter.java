package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import com.jsf.conventions.converter.AbstractBaseConverter;
import com.jsf.conventions.qualifier.CustomService;
import com.jsf.conventions.qualifier.PersistentClass;
import com.jsf.conventions.service.BaseService;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.UsuarioService;

@Advanced
@FacesConverter(value="usuarioConverter",forClass=Usuario.class)
public class UsuarioConverter extends AbstractBaseConverter {
		 @Inject
		 public void setService(@CustomService(entity=Usuario.class) BaseService usuarioService){
			 super.setBaseService(usuarioService);
		 }

}
