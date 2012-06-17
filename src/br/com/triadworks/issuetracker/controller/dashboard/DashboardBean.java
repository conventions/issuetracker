package br.com.triadworks.issuetracker.controller.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.com.triadworks.issuetracker.controller.UsuarioWeb;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.service.IssueService;

@Named
@ViewAccessScoped
public class DashboardBean implements Serializable{

	private List<Issue> issues = new ArrayList<Issue>();
	
	private IssueService issueService;
	private UsuarioWeb usuarioWeb;
	
	
	
	public DashboardBean() {
		 
	}

	@Inject
	public DashboardBean(IssueService issueService, UsuarioWeb usuarioWeb) {
		this.issueService = issueService;
		this.usuarioWeb = usuarioWeb;
	}
	
	@PostConstruct
	public void preload() {
		Long id = usuarioWeb.getUsuario().getId();
		issues = issueService.getIssuesDoUsuario(id);
	}
	
	public List<Issue> getIssues() {
		return issues;
	}
	
}
