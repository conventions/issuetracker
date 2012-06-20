package br.com.triadworks.issuetracker.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsf.conventions.producer.entitymanager.CustomEntityManagerProvided;
import com.jsf.conventions.producer.entitymanager.EntityManagerProvider;
import com.jsf.conventions.producer.entitymanager.Type;
import com.jsf.conventions.qualifier.ConventionsEntityManager;

@RequestScoped
@Specializes
@ConventionsEntityManager(type=Type.CUSTOM)
public class IssueTrackerEntityManagerProvider extends CustomEntityManagerProvided implements EntityManagerProvider{
	
	@PersistenceContext(unitName="issueTrackerPU")
	private EntityManager entityManager;
	
	
	@Produces
    @RequestScoped
    @Default
    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


	public void dispose(@Disposes @Default EntityManager entityManager) {
	 
		if(entityManager.isOpen()){
			entityManager.close();
		}
	}

   
}
