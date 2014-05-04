package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;
import org.conventionsframework.converter.BaseConverter;
import org.conventionsframework.qualifier.Service;
import org.conventionsframework.service.BaseService;

import br.com.triadworks.issuetracker.model.Issue;

@Advanced
@FacesConverter(value="issueConverter",forClass=Issue.class)
public class IssueConverter extends BaseConverter{


	@Inject
	public void setService(@Service BaseService<Issue> service){
		super.setBaseService(service);
	}

}
