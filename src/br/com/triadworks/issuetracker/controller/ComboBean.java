package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.conventionsframework.qualifier.Service;
import org.conventionsframework.service.BaseService;

import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.model.Usuario;

@Named
@ViewAccessScoped
public class ComboBean implements Serializable {

	private List<Projeto> projetos;
	
	private List<Usuario> usuarios;
	
	@Inject @Service(entity=Usuario.class)
	private BaseService usuarioService;
	
	@Inject @Service(entity=Projeto.class)
	private BaseService projetoService;
	
	
	public void setProjetoService(){
		
	}

	public List<Projeto> getProjetos() {
		if(projetos == null){
			projetos = projetoService.findAll();
		}
		return projetos;
	}

	public List<Usuario> getUsuarios() {
		if(usuarios == null){
			usuarios = usuarioService.findAll();
		}
		return usuarios;
	}

	public BaseService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(BaseService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public BaseService getProjetoService() {
		return projetoService;
	}

	public void setProjetoService(BaseService projetoService) {
		this.projetoService = projetoService;
	}

	
	 

	
	
}
