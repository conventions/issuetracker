package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.triadworks.issuetracker.dao.IssueService;
import br.com.triadworks.issuetracker.model.Issue;

@FacesConverter(forClass=Issue.class)
@Advanced
public class IssueConverter implements Converter {

	@Inject
	private IssueService dao;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		if (value == null)
			return null;
		
		Long id = new Long(value);
		
		Issue issue = dao.carrega(id);
		return issue;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		if (value == null)
			return null;
		
		Issue issue = (Issue) value;
		return issue.getId().toString();
	}

}
