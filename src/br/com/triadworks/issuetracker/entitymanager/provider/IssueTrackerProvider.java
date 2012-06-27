package br.com.triadworks.issuetracker.entitymanager.provider;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.myfaces.extensions.cdi.jpa.api.TransactionScoped;

import com.jsf.conventions.entitymanager.provider.CustomEntityManagerProvider;
import com.jsf.conventions.entitymanager.provider.EntityManagerProvider;
import com.jsf.conventions.entitymanager.provider.Type;
import com.jsf.conventions.qualifier.ConventionsEntityManager;

@Specializes
@ConventionsEntityManager(type=Type.CUSTOM)
@RequestScoped
public class IssueTrackerProvider extends CustomEntityManagerProvider implements EntityManagerProvider{
	
	 
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="issueTrackerPU")
	private EntityManager entityManager;

	/**
	 * The producer is required so CODI can handlle @Transactional methods
	 */
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return entityManager;
	}

   
	public void dispose(@Disposes EntityManager entityManager){
		if(entityManager.isOpen()){
			entityManager.close();
		}
	}
}
