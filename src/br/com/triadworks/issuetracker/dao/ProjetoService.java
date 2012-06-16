package br.com.triadworks.issuetracker.dao;

import java.util.List;

import br.com.triadworks.issuetracker.model.Projeto;

import com.jsf.conventions.service.BaseService;

public interface ProjetoService extends BaseService<Projeto,Long>{

	public List<Projeto> listaTudo();

	public void salva(Projeto projeto);

	public void atualiza(Projeto projeto);

	public void remove(Projeto projeto);

	public Projeto carrega(Long id);
	
}
