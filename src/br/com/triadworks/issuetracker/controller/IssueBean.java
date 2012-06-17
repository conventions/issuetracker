package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.SelectEvent;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.model.TipoDeIssue;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.IssueService;
import br.com.triadworks.issuetracker.service.UsuarioService;

import com.jsf.conventions.bean.BaseMBean;

@Named
@ViewAccessScoped
public class IssueBean extends BaseMBean<Issue> implements Serializable{
	
	private static final String ESTADO_DE_NOVO = "_novo";
	private static final String ESTADO_DE_EDICAO = "_edicao";
	private static final String ESTADO_DE_PESQUISA = "_pesquisa";
	
	private String state = ESTADO_DE_PESQUISA;
	
	private Issue issue = new Issue();
	private List<Issue> issues = new ArrayList<Issue>();
	
	private UsuarioWeb usuarioWeb;
	private FacesUtils facesUtils;
	
	private List<Usuario> usuarios;
	@Inject
	private UsuarioService usuarioService;
	
	
   //contrutor padrao necessario para o container cdi "bootar"
	public IssueBean() {
	}

	@Inject
	public IssueBean(IssueService issueService, UsuarioWeb usuarioWeb, FacesUtils facesUtils) {
		super.setBaseService(issueService);
		this.usuarioWeb = usuarioWeb;
		this.facesUtils = facesUtils;
	}

	IssueService getIssueService(){
		return (IssueService) super.getBaseService();
	}
	
	public void lista() {
		setState(ESTADO_DE_PESQUISA);
	}
	
	public void preparaParaAdicionar() {
		this.issue = new Issue();
//		issue.setProjeto(new Projeto()); // não mais necessário por causa do converter
//		issue.setAssinadoPara(new Usuario());  // não mais necessário por causa do converter
		issue.setReportadoPor(usuarioWeb.getUsuario());
		issue.setReportadoEm(new Date());
		setState(ESTADO_DE_NOVO);
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
		this.issue = getIssueService().carrega(projeto.getId()); // evita LazyInitializationException
		setState(ESTADO_DE_EDICAO);
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
		return ESTADO_DE_NOVO.equals(state);
	}
	public boolean isEditando() {
		return ESTADO_DE_EDICAO.equals(state);
	}
	public boolean isPesquisando() {
		return ESTADO_DE_PESQUISA.equals(state);
	}
	
	public List<Issue> getIssues() {
		return issues;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue projeto) {
		this.issue = projeto;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Produces
	@Named("issueTypes")
	public List<SelectItem> getIssueTypes(){
		return new ArrayList<SelectItem>(){{add(new SelectItem(TipoDeIssue.BUG.name(), "Bug"));add(new SelectItem(TipoDeIssue.FEATURE.name(), "Feature"));}};
	}

	public List<Usuario> getUsuarios() {
		if(usuarios == null){
			usuarios = usuarioService.listaTudo();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void selecionaUsuario(SelectEvent event){
		issue.setAssinadoPara((Usuario) event.getObject());
	}
	
	
}





