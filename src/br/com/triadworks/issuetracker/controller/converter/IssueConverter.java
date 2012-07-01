package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.triadworks.issuetracker.model.Issue;

import com.jsf.conventions.converter.AbstractBaseConverter;
import com.jsf.conventions.qualifier.CustomService;
import com.jsf.conventions.service.BaseService;

@Advanced
@FacesConverter(value="issueConverter",forClass=Issue.class)
public class IssueConverter extends AbstractBaseConverter{


	@Inject
	public void setService(@CustomService(entity=Issue.class) BaseService service){
		super.setBaseService(service);
	}

}
