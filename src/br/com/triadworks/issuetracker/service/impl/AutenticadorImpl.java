package br.com.triadworks.issuetracker.service.impl;

import javax.inject.Inject;

import com.jsf.conventions.qualifier.CustomService;
import com.jsf.conventions.qualifier.PersistentClass;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.Autenticador;
import br.com.triadworks.issuetracker.service.UsuarioService;

public class AutenticadorImpl implements Autenticador {

	private static final long serialVersionUID = 1L;
	
		@Inject @PersistentClass(Usuario.class)	
		private UsuarioService usuarioService;


		@Override
		public Usuario autentica(String login, String senha) {
			Usuario usuario = usuarioService.buscaPor(login, senha);
			return usuario;
		}

	}
