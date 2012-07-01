package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.ProjetoService;

import com.jsf.conventions.converter.AbstractBaseConverter;
import com.jsf.conventions.qualifier.CustomService;
import com.jsf.conventions.service.BaseService;

@Advanced
@FacesConverter(value="projetoConverter",forClass=Projeto.class)
public class ProjetoConverter extends AbstractBaseConverter{

	
	 @Inject
	 public void setService(@CustomService(entity=Projeto.class) BaseService  projetoService){
		 super.setBaseService(projetoService);
	 }
}
