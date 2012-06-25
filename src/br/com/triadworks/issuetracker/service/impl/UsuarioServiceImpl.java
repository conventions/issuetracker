package br.com.triadworks.issuetracker.service.impl;

import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.UsuarioService;

import com.jsf.conventions.exception.BusinessException;
import com.jsf.conventions.service.impl.CustomHibernateService;

@Named("usuarioService")
public class UsuarioServiceImpl extends CustomHibernateService<Usuario, Long> implements UsuarioService {


	private static final long serialVersionUID = 1L;

	@Override
	public List<Usuario> listaTudo() {
		return getDao().findAll();
	}

	@Override
	public void salva(Usuario usuario) {
		if(isUsuarioExistente(usuario)){
			throw new BusinessException("Usuário com o login:"+usuario.getLogin() + " já cadastrado em nossa base de dados.");
		}
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

	@Override
	public boolean isUsuarioExistente(Usuario usuario) {
		DetachedCriteria dc = getDetachedCriteria(); 
		//usando para ignorar id do usuario que estamos editando senão o rowCount retorna o proprio usuario
		if(usuario.getId() != null){
			dc.add(Restrictions.ne("id", usuario.getId()));
		}

		if(usuario != null && !"".endsWith(usuario.getLogin())){
			dc.add(Restrictions.ilike("login", usuario.getLogin(), MatchMode.EXACT));
			return (super.getRowCount(dc) > 0);
 		}
		return false;
	}
	 
}
