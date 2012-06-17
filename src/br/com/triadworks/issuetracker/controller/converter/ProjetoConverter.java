package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.service.ProjetoService;

import com.jsf.conventions.converter.AbstractBaseConverter;

@Advanced
@FacesConverter(value="projetoConverter",forClass=Projeto.class)
public class ProjetoConverter extends AbstractBaseConverter{

	
	@Inject
	 public void setService(ProjetoService projetoService){
		 super.setBaseService(projetoService);
	 }
}
