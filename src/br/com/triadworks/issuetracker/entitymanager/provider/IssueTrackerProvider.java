package br.com.triadworks.issuetracker.entitymanager.provider;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.conventionsframework.entitymanager.CustomEntityManagerProvider;
import org.conventionsframework.entitymanager.EntityManagerProvider;
import org.conventionsframework.qualifier.ConventionsEntityManager;
import org.conventionsframework.qualifier.Type;


@Specializes
@ConventionsEntityManager(type=Type.CUSTOM)
@RequestScoped
public class IssueTrackerProvider extends CustomEntityManagerProvider implements EntityManagerProvider{
	
	 
	private static final long serialVersionUID = 1L;
	
 
	private EntityManager entityManager;
	
	 
	public  IssueTrackerProvider(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("issueTrackerPU");
		entityManager = emf.createEntityManager();
		
	}

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
