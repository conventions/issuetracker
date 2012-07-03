package br.com.triadworks.issuetracker.service.impl;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.primefaces.model.SortOrder;

import br.com.triadworks.issuetracker.model.Comentario;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.model.TipoDeIssue;
import br.com.triadworks.issuetracker.service.IssueService;

import com.jsf.conventions.exception.BusinessException;
import com.jsf.conventions.model.WrappedData;
import com.jsf.conventions.qualifier.PersistentClass;
import com.jsf.conventions.service.impl.CustomHibernateService;

@Dependent
@Named("issueService")
@PersistentClass(Issue.class)
public class IssueServiceImpl extends CustomHibernateService<Issue, Long>
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
	@org.apache.myfaces.extensions.cdi.jpa.api.Transactional
	public void comenta(Long id, Comentario comentario) {
		Issue issue = this.load(id);
		issue.comenta(comentario); // thanks persistence context ;-)
	}

	@Override
	@org.apache.myfaces.extensions.cdi.jpa.api.Transactional
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
	public void beforeStore(Issue issue) {
		if(issue.getAssinadoPara() == null){
			 throw new BusinessException("Assinado para: campo obrigatório");
		}
		super.beforeStore(issue);
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
	public WrappedData<Issue> configFindPaginated(int first,
			int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters, Map<String, Object> externalFilter) {
		
		
		DetachedCriteria dc = getDetachedCriteria();
		
		//configura paginação para o dashboard
		Long idUsuario = (Long) externalFilter.get("uID");//parametro passado atraves do mapa de parametros {@see DashboardBean#preload()}
		if(idUsuario != null){
			dc.createAlias("assinadoPara", "assinadoPara");
			dc.add(Restrictions.eq("assinadoPara.id", idUsuario));
		}
		
		
		String nomeProjeto = null;
		
		// configura filtros das colunas da tabela, somente necessário se houver relacionamentos(ex:issue->projeto)
		// ou para alterar comportamento padrão dos filtros {@see StandaloneHenericHibernateDao#addBasicFilterRestrictions}
		if (filters != null && !filters.isEmpty()) {
			
			String id = filters.get("id");
			
			if(id != null && !"".endsWith(id)){
				try{
					dc.add(Restrictions.eq("id", Long.parseLong(id)));
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		    nomeProjeto = filters.get("projeto.nome");
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
				else if(TipoDeIssue.FEATURE.name().equals(tipo)){
					dc.add(Restrictions.eq("tipo", TipoDeIssue.FEATURE));
				}
			}
		}
		//cria join para ordenar por "assinadoPara"
		if(sortField != null && sortField.equals("assinadoPara.nome") && idUsuario == null){ //se idUsuario for != null é pq o alias ja foi criado
			dc.createAlias("assinadoPara", "assinadoPara",JoinType.LEFT_OUTER_JOIN);
		}
		if(sortField != null && sortField.equals("projeto.nome") && nomeProjeto == null){//se nome projeto != null é pq o alias ja foi criado
			dc.createAlias("projeto", "projeto",JoinType.LEFT_OUTER_JOIN);
		}
		return getDao().findPaginated(first, pageSize, sortField, sortOrder,dc);
	}

}
