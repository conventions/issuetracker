package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.inject.Named;

import br.com.triadworks.issuetracker.dao.ProjetoService;
import br.com.triadworks.issuetracker.model.Projeto;

import com.jsf.conventions.service.impl.StandaloneHibernateService;

@Named("projetoDao")//é acessado diretamente na tela(combo de projetos)
public class ProjetoServiceImpl extends StandaloneHibernateService<Projeto, Long> implements ProjetoService {


	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> listaTudo() {
		return getDao().findAll();
	}

	@Override
	public void salva(Projeto projeto) {
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


}
