package br.com.triadworks.issuetracker.controller.dashboard;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.conventionsframework.bean.BaseMBean;

import br.com.triadworks.issuetracker.controller.UsuarioWeb;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.service.IssueService;

@Named
@ViewAccessScoped
public class DashboardBean extends BaseMBean<Issue> implements Serializable{

	
	private UsuarioWeb usuarioWeb;
	
	public DashboardBean() {
		 
	}

	@Inject
	public DashboardBean(IssueService issueService, UsuarioWeb usuarioWeb) {
		super.setBaseService(issueService);
		this.usuarioWeb = usuarioWeb;
	}
	
	/**
	 * coloca id do usuario no mapa para issueService acessar posteriormente
	 * quando for configurar a paginação(configFindPaginated)
	 */
	@PostConstruct
	public void preload() {
		init();
		Long id = usuarioWeb.getUsuario().getId();
		getSearchModel().addFilter("uID", id);
//		issues = issueService.getIssuesDoUsuario(id);
	}
	
}
