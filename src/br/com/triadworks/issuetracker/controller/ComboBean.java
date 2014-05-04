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
	
	/*
	 * services genericas, apenas para queries em banco
	 * para regras de negocio @see ProjetoService  e @see IssueService
	 */
	@Inject @Service
	private BaseService<Usuario> usuarioService;
	
	@Inject @Service
	private BaseService<Projeto> projetoService;
	
	
	public void setProjetoService(){
		
	}

	public List<Projeto> getProjetos() {
		if(projetos == null){
			projetos = projetoService.getDao().findAll();
		}
		return projetos;
	}

	public List<Usuario> getUsuarios() {
		if(usuarios == null){
			usuarios = usuarioService.getDao().findAll();
		}
		return usuarios;
	}

	
}
