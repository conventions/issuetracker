package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.primefaces.model.SortOrder;

import br.com.triadworks.issuetracker.dao.IssueService;
import br.com.triadworks.issuetracker.model.Comentario;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.model.TipoDeIssue;

import com.jsf.conventions.model.ConventionsDataModel;
import com.jsf.conventions.service.impl.StandaloneHibernateService;

@Named("issueDao")
public class IssueServiceImpl extends StandaloneHibernateService<Issue, Long>
		implements IssueService {

	private static final long serialVersionUID = 1L;

	@Override
	public Issue carrega(Long id) {
		return getEntityManager().find(Issue.class, id);
	}

	@Override
	public List<Issue> getIssuesDoUsuario(Long id) {
		DetachedCriteria dc = getDetachedCriteria();
		dc.createAlias("assinadoPara", "assinadoPara");
		dc.add(Restrictions.eq("assinadoPara.id", id));
		return findByCriteria(dc);
	}

	@Override
	@Transactional
	public void comenta(Long id, Comentario comentario) {
		Issue issue = carrega(id);
		issue.comenta(comentario); // thanks persistence context ;-)
	}

	@Override
	@Transactional
	public void fecha(Long id, Comentario comentario) {
		Issue issue = carrega(id);
		issue.fecha(comentario); // thanks persistence context ;-)
	}

	@Override
	public List<Issue> listaTudo() {
		return getDao().findAll();
	}

	@Override
	public void salva(Issue issue) {
		super.store(issue);

	}

	@Override
	public void remove(Issue issue) {
		super.remove((Issue) getDao().load(issue.getId()));
	}

	@Override
	public void atualiza(Issue issue) {
		super.saveOrUpdate(issue);

	}

	/**
	 * metodo invocado toda vez que uma lazy datatable de um MB(ex:  <p:datatable value="#{issueBean.dataModel}") que usa
	 * esta service for atualizada(via ajax ou não)
	 */
	@Override
	public ConventionsDataModel<Issue> configFindPaginated(int first,
			int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters, Map<String, Object> externalFilter) {
		DetachedCriteria dc = getDetachedCriteria();
		
		
		// configura filtros das colunas da tabela, somente necessário se houver relacionamentos(ex:issue->projeto)
		// ou para alterar comportamento padrão dos filtros (@see StandaloneHenericHibernateDao#addBasicFilterRestrictions)
		if (filters != null && !filters.isEmpty()) {
			String nomeProjeto = filters.get("projeto.nome");
			if(nomeProjeto != null){
				dc.createAlias("projeto", "projeto");
				dc.add(Restrictions.ilike("projeto.nome", nomeProjeto,MatchMode.ANYWHERE));
			}
			String sumario = filters.get("sumario");
			if(sumario != null){
				dc.add(Restrictions.ilike("sumario", sumario,MatchMode.ANYWHERE));
			}
			
			String tipo = filters.get("tipo");
			if(tipo != null){
				if(TipoDeIssue.BUG.name().equals(tipo)){
					dc.add(Restrictions.eq("tipo", TipoDeIssue.BUG));
				}
				else{
					dc.add(Restrictions.eq("tipo", TipoDeIssue.FEATURE));
				}
			}
		}
		//cria join para ordenar por "assinadoPara"
		if(sortField != null && sortField.equals("assinadoPara.nome")){
			dc.createAlias("assinadoPara", "assinadoPara",JoinType.LEFT_OUTER_JOIN);
		}
		return getDao().findPaginated(first, pageSize, sortField, sortOrder,dc);
	}

}
