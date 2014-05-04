package br.com.triadworks.issuetracker.service;

import java.util.List;

import org.conventionsframework.service.BaseService;

import br.com.triadworks.issuetracker.model.Projeto;

public interface ProjetoService extends BaseService<Projeto>{

	public List<Projeto> listaTudo();

	public void salva(Projeto projeto);

	public void atualiza(Projeto projeto);

	public void remove(Projeto projeto);

	public Projeto carrega(Long id);
	
	boolean isProjetoExistente(Projeto projeto);
	
	
}
