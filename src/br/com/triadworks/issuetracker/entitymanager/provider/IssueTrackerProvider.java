package br.com.triadworks.issuetracker.entitymanager.provider;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsf.conventions.entitymanager.provider.CustomEntityManagerProvided;
import com.jsf.conventions.entitymanager.provider.EntityManagerProvider;
import com.jsf.conventions.entitymanager.provider.Type;
import com.jsf.conventions.qualifier.ConventionsEntityManager;

@Specializes
@Dependent
@ConventionsEntityManager(type=Type.CUSTOM)
public class IssueTrackerProvider extends CustomEntityManagerProvided implements EntityManagerProvider{
	
	 
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="issueTrackerPU")
	private EntityManager entityManager;

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

   
}
