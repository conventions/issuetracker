package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.triadworks.issuetracker.dao.UsuarioService;
import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.model.Usuario;

import com.jsf.conventions.service.impl.StandaloneHibernateService;

@Named("usuarioDao")
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
