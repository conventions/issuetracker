package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIForm;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.service.ProjetoService;

@ViewAccessScoped
@Named
public class ProjetoBean implements Serializable{
	
	private Projeto projeto = new Projeto();
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	private static final String ESTADO_DE_NOVO = "_novo";
	private static final String ESTADO_DE_EDICAO = "_edicao";
	private static final String ESTADO_DE_PESQUISA = "_pesquisa";
	
	private String state = ESTADO_DE_PESQUISA;
	
	private UIForm form;
	
	@Inject
	private ProjetoService projetoService;
	@Inject
	private FacesUtils facesUtils;
	
	public void lista() {
		projetos = projetoService.listaTudo();
		setState(ESTADO_DE_PESQUISA);
	}
	
	public void preparaParaAdicionar() {
		this.projeto = new Projeto();
		setState(ESTADO_DE_NOVO);
	}
	
	public void adiciona() {
		projetoService.salva(projeto);
		facesUtils.adicionaMensagemDeInformacao("Projeto adicionado com sucesso!");
		lista();
	}
	
	public void remove() {
		projetoService.remove(projeto);
		facesUtils.adicionaMensagemDeInformacao("Projeto removido com sucesso!");
		lista();
	}
	
	public void preparaParaAlterar(Projeto projeto) {
		this.projeto = projetoService.carrega(projeto.getId()); // evita LazyInitializationException
		setState(ESTADO_DE_EDICAO);
	}
	
	public void altera() {
		projetoService.atualiza(projeto);
		facesUtils.adicionaMensagemDeInformacao("Projeto atualizado com sucesso!");
		lista();
	}
	
	public void voltar() {
		this.projeto = new Projeto();
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
	
	public List<Projeto> getprojetos() {
		return projetos;
	}
	public void setProjetoDao(ProjetoService projetoDao) {
		this.projetoService = projetoDao;
	}
	public Projeto getprojeto() {
		return projeto;
	}
	public void setprojeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}
	public void setprojetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public UIForm getForm() {
		return form;
	}
	public void setForm(UIForm form) {
		this.form = form;
	}
	
}





