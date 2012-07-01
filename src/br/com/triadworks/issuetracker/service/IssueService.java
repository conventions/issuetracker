package br.com.triadworks.issuetracker.service;

import java.util.List;

import br.com.triadworks.issuetracker.model.Comentario;
import br.com.triadworks.issuetracker.model.Issue;

import com.jsf.conventions.service.BaseService;

public interface IssueService extends BaseService<Issue, Long> {

	List<Issue> listaTudo();

	void salva(Issue issue);

	void atualiza(Issue issue);

	void remove(Issue issue);

	Issue carrega(Long id);

	List<Issue> getIssuesDoUsuario(Long id);

	void comenta(Long id, Comentario comentario);

	void fecha(Long id, Comentario comentario);
}
