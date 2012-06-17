package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.Autenticador;
import br.com.triadworks.issuetracker.service.UsuarioService;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private String login;
	private String senha;

	@Inject
	private Autenticador autenticador;
	
	@Inject
	private UsuarioWeb usuarioWeb;
	
	@Inject
	private FacesUtils facesUtils;

	@Inject
	private UsuarioService usuarioService;

	public String logar() {

		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario);
			return "/home?faces-redirect=true"; // outcome
		}

		facesUtils.adicionaMensagemDeErro("Login ou senha invalida.");
		return null;
	}

	@PostConstruct
	public void initUser() {
		List<Usuario> usuarios = usuarioService.listaTudo();
		if (usuarios == null || usuarios.isEmpty()) {
			Usuario admin = new Usuario();
			admin.setEmail("admin@admin.com");
			admin.setLogin("admin");
			admin.setSenha("admin");
			admin.setNome("Administrator Godlike");
			usuarioService.salva(admin);
		}
	}

	public String sair() {
		usuarioWeb.logout();
		return "login";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAutenticador(Autenticador autenticador) {
		this.autenticador = autenticador;
	}

	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}

	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}

}
