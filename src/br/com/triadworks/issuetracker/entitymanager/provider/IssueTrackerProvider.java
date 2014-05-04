package br.com.triadworks.issuetracker.entitymanager.provider;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.conventionsframework.producer.DefaultEntityManagerProducer;


@RequestScoped
@Specializes
public class IssueTrackerProvider extends DefaultEntityManagerProducer{
	
	 
	private EntityManager entityManager;
	
	 
	public  IssueTrackerProvider(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("issueTrackerPU");
		entityManager = emf.createEntityManager();
		
	}

	/**
	 * The producer is required so CODI can handlle @Transactional methods and
	 * also conventions needs for entity manager injection in BaseService
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
