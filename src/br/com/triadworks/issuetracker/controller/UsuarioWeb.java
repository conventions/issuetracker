package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.annotations.Index;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.qualifier.UsuarioLogado;

@SessionScoped
@Named("usuarioWeb")
public class UsuarioWeb implements Serializable{

	private Usuario usuario;
	
	public void loga(Usuario usuario) {
		this.usuario = usuario;
		
	}

	public void logout() {
		this.usuario = null;
	}
	
	public boolean isLogado() {
		return this.usuario != null;
	}

	@Produces
	@UsuarioLogado
	@Named("usuarioLogado")
	public Usuario getUsuario() {
		return usuario;
	}
	
}
