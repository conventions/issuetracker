package br.com.triadworks.issuetracker.service;

import java.util.List;

import org.conventionsframework.service.BaseService;

import br.com.triadworks.issuetracker.model.Usuario;

public interface UsuarioService extends BaseService<Usuario, Long>{

	public List<Usuario> listaTudo();

	public void salva(Usuario usuario);

	public void atualiza(Usuario usuario);

	public void remove(Usuario usuario);

	public Usuario buscaPor(String login, String senha);

	public Usuario carrega(Long id);
	
	boolean isUsuarioExistente(Usuario usuario);
	
}
