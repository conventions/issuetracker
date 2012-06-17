package br.com.triadworks.issuetracker.service.impl;

import javax.inject.Inject;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.Autenticador;
import br.com.triadworks.issuetracker.service.UsuarioService;

public class AutenticadorImpl implements Autenticador {

	private static final long serialVersionUID = 1L;
		private UsuarioService usuarioService;

		@Inject
		public AutenticadorImpl(UsuarioService usuarioService) {
			this.usuarioService = usuarioService;
		}

		@Override
		public Usuario autentica(String login, String senha) {
			Usuario usuario = usuarioService.buscaPor(login, senha);
			return usuario;
		}

	}
