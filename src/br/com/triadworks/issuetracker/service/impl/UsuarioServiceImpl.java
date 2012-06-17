package br.com.triadworks.issuetracker.service.impl;

import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.Restrictions;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.UsuarioService;

import com.jsf.conventions.service.impl.StandaloneHibernateService;

@Named("usuarioService")
public class UsuarioServiceImpl extends StandaloneHibernateService<Usuario, Long> implements UsuarioService {


	@Override
	public List<Usuario> listaTudo() {
		return getDao().findAll();
	}

	@Override
	public void salva(Usuario usuario) {
		super.store(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		super.remove((Usuario)getDao().load(usuario.getId()));
	}

	@Override
	public void atualiza(Usuario usuario) {
		super.saveOrUpdate(usuario);
		
	}

	@Override
	public Usuario carrega(Long id) {
		return getEntityManager().find(Usuario.class, id);
	}

	@Override
	public Usuario buscaPor(String login, String senha) {
		return (Usuario) getCriteria().add(Restrictions.eq("login", login))
				.add(Restrictions.eq("senha", senha)).uniqueResult();
	}


}
