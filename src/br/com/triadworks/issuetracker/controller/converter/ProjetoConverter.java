package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;
import org.conventionsframework.converter.BaseConverter;
import org.conventionsframework.qualifier.Service;
import org.conventionsframework.service.BaseService;

import br.com.triadworks.issuetracker.model.Projeto;

@Advanced
@FacesConverter(value="projetoConverter",forClass=Projeto.class)
public class ProjetoConverter extends BaseConverter{

	
	 @Inject
	 public void setService(@Service BaseService<Projeto> projetoService){
		 super.setBaseService(projetoService);
	 }
}
