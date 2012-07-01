package br.com.triadworks.issuetracker.service.impl;

import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.service.ProjetoService;

import com.jsf.conventions.exception.BusinessException;
import com.jsf.conventions.qualifier.PersistentClass;
import com.jsf.conventions.service.impl.CustomHibernateService;

@Named("projetoService")//é acessado diretamente na tela(combo de projetos)
@PersistentClass(Projeto.class)
public class ProjetoServiceImpl extends CustomHibernateService<Projeto, Long> implements ProjetoService {


	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> listaTudo() {
		return getDao().findAll();
	}

	@Override
	public void salva(Projeto projeto) {
		if(isProjetoExistente(projeto)){
			throw new BusinessException("Projeto com o nome:"+projeto.getNome() + " já existe em nossa base de dados.");
		}
		super.store(projeto);
	}

	@Override
	public void remove(Projeto projeto) {
		super.remove((Projeto)getDao().load(projeto.getId()));
	}

	@Override
	public void atualiza(Projeto projeto) {
		super.saveOrUpdate(projeto);
		
	}

	@Override
	public Projeto carrega(Long id) {
		return getEntityManager().find(Projeto.class, id);
	}

	@Override
	public boolean isProjetoExistente(Projeto projeto) {
		DetachedCriteria dc = getDetachedCriteria(); 
		//usando para ignorar id do projeto que estamos editando senão o rowCount retorna o proprio projeto
		if(projeto.getId() != null){
			dc.add(Restrictions.ne("id", projeto.getId()));
		}

		if(projeto != null && !"".endsWith(projeto.getNome())){
			dc.add(Restrictions.ilike("nome", projeto.getNome(), MatchMode.EXACT));
			return (super.getRowCount(dc) > 0);
 		}
		return false;
	}

}
