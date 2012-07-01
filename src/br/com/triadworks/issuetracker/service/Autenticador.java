package br.com.triadworks.issuetracker.service;

import java.io.Serializable;

import br.com.triadworks.issuetracker.model.Usuario;

public interface Autenticador extends Serializable{

	public Usuario autentica(String login, String senha);

}
