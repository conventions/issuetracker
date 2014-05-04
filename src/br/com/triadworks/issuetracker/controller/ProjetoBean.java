package br.com.triadworks.issuetracker.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.conventionsframework.bean.BaseMBean;
import org.conventionsframework.bean.state.CrudState;
import org.conventionsframework.paginator.Paginator;
import org.conventionsframework.qualifier.Service;
import org.conventionsframework.service.BaseService;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.service.ProjetoService;

@ViewAccessScoped
@Named
public class ProjetoBean extends BaseMBean<Projeto> {
	
	private Projeto projeto = new Projeto();
	
	@Inject
	private ProjetoService projetoService;
	
 
	@Inject
	private FacesUtils facesUtils;
	
	public void lista() {
		setBeanState(CrudState.FIND);
	}
	
	@PostConstruct
	public void init(){
		super.setBaseService(projetoService);
		setBeanState(CrudState.FIND);
		super.init();
	}
	
	public void preparaParaAdicionar() {
		this.projeto = new Projeto();
		setBeanState(CrudState.INSERT);
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
		this.projeto = projetoService.getDao().get(projeto.getId()); // evita LazyInitializationException
		setBeanState(CrudState.UPDATE);
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
		return super.isInsertState();
	}
	public boolean isEditando() {
		return super.isUpdateState();
	}
	public boolean isPesquisando() {
		return super.isFindState();
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
	
}
