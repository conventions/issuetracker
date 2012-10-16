package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.conventionsframework.bean.BaseMBean;
import org.conventionsframework.bean.state.CrudState;
import org.primefaces.event.SelectEvent;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.model.TipoDeIssue;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.IssueService;

@Named
@ViewAccessScoped
public class IssueBean extends BaseMBean<Issue> implements Serializable{
	
	
	private Issue issue = new Issue();
	
	private UsuarioWeb usuarioWeb;
	private FacesUtils facesUtils;

	public IssueBean() {
	}

	@Inject
	public IssueBean(UsuarioWeb usuarioWeb, FacesUtils facesUtils) {
		
		this.usuarioWeb = usuarioWeb;
		this.facesUtils = facesUtils;
		this.setBeanState(CrudState.FIND);
	}
	
	@Inject
	public void setBeanService(IssueService issueService){
		super.setBaseService(issueService);
	}

	IssueService getIssueService(){
		return (IssueService) super.getBaseService();
	}
	
	public void lista() {
		setBeanState(CrudState.FIND);
	}
	
	public void preparaParaAdicionar() {
		this.issue = new Issue();
//		issue.setProjeto(new Projeto()); // não mais necessário por causa do converter
//		issue.setAssinadoPara(new Usuario());  // não mais necessário por causa do converter
		issue.setReportadoPor(usuarioWeb.getUsuario());
		issue.setReportadoEm(new Date());
		setBeanState(CrudState.INSERT);
	}
	
	public void adiciona() {
		getIssueService().salva(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue adicionada com sucesso!");
		lista();
	}
	
	public void remove() {
		getIssueService().remove(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue removida com sucesso!");
		lista();
	}
	
	public void preparaParaAlterar(Issue projeto) {
		this.issue = getIssueService().get(projeto.getId()); // evita LazyInitializationException
		setBeanState(CrudState.UPDATE);
	}
	
	public void altera() {
		getIssueService().atualiza(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue atualizada com sucesso!");
		lista();
	}
	
	public void voltar() {
		this.issue = new Issue();
		lista();
	}
	
	public boolean isAdicionando() {
		return super.isInsertState();
	}
	public boolean isEditando() {
		return super.isUpdateState();
	}
	public boolean isPesquisando() {
		return super.isFindState();
	}
	
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue projeto) {
		this.issue = projeto;
	}
	
	@Produces
	@Named("issueTypes")
	public List<SelectItem> getIssueTypes() {
		List<SelectItem> tipos = new ArrayList<SelectItem>(){{
			add(new SelectItem(TipoDeIssue.TODOS.name(), "Todos"));
			add(new SelectItem(TipoDeIssue.BUG.name(), "Bug"));
			add(new SelectItem(TipoDeIssue.FEATURE.name(), "Feature"));
		}};
		if(!isPesquisando()){
			tipos.remove(0);
		}
	   return tipos;
	}

	
	public void selecionaUsuario(SelectEvent event){
		issue.setAssinadoPara((Usuario) event.getObject());
	}
	
	
}





