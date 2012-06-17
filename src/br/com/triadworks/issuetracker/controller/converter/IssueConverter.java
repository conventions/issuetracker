package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import com.jsf.conventions.converter.AbstractBaseConverter;

import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.service.IssueService;

@Advanced
@FacesConverter(value="issueConverter",forClass=Issue.class)
public class IssueConverter extends AbstractBaseConverter{


	@Inject
	public void setService(IssueService service){
		super.setBaseService(service);
	}

}
