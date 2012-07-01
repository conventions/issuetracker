package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.qualifier.UserRole;
import br.com.triadworks.issuetracker.qualifier.UsuarioLogado;

@SessionScoped
@Named("usuarioWeb")
public class UsuarioWeb implements Serializable{

	private Usuario usuario;
	
	@Produces @UserRole
	private String userRole;

	public void loga(Usuario usuario) {
		if(usuario.getLogin().equalsIgnoreCase("admin")){
			userRole = "godlike";
		}
		else{
			userRole = "";
		}
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
