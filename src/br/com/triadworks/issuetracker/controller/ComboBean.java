package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.ProjetoService;
import br.com.triadworks.issuetracker.service.UsuarioService;

@Named
@ViewAccessScoped
public class ComboBean implements Serializable {

	private List<Projeto> projetos;
	
	private List<Usuario> usuarios;
	
	@Inject 
	private ProjetoService projetoService;
	
	@Inject 
	private UsuarioService usuarioService;

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

	
	 

	
	
}
