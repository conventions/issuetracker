package br.com.triadworks.issuetracker.service.impl;

import java.util.List;

import javax.inject.Named;

import org.conventionsframework.exception.BusinessException;
import org.conventionsframework.service.impl.BaseServiceImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.service.ProjetoService;

@Named("projetoService")//é acessado diretamente na tela(combo de projetos)
public class ProjetoServiceImpl extends BaseServiceImpl<Projeto> implements ProjetoService {


	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> listaTudo() {
		return crud.listAll();
	}

	@Override
	public void salva(Projeto projeto) {
		if(isProjetoExistente(projeto)){
			throw new BusinessException("Projeto com o nome:"+projeto.getNome() + " já existe em nossa base de dados.");
		}
		super.store(projeto);
	}


	@Override
	public void atualiza(Projeto projeto) {
		crud.saveOrUpdate(projeto);
		
	}

	@Override
	public Projeto carrega(Long id) {
		return getEntityManager().find(Projeto.class, id);
	}

	@Override
	public boolean isProjetoExistente(Projeto projeto) {
		Criteria crit = getCriteria(); 
		//usando para ignorar id do projeto que estamos editando senão o rowCount retorna o proprio projeto
		if(projeto.getId() != null){
			crit.add(Restrictions.ne("id", projeto.getId()));
		}

		if(projeto != null && !"".endsWith(projeto.getNome())){
			crit.add(Restrictions.ilike("nome", projeto.getNome(), MatchMode.EXACT));
			return (crud.criteria(crit).count() > 0);
 		}
		return false;
	}

}
