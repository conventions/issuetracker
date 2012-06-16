package br.com.triadworks.issuetracker.producer;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class EntityManagerProducer implements Serializable{
	
	@PersistenceContext(unitName="issueTrackerPU")
	private EntityManager entityManager;

	@Produces @RequestScoped @Default
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	 public void dispose(@Disposes @Default EntityManager entityManager)
	    {
	        if(entityManager.isOpen())
	        {
	            entityManager.close();
	        }
	    } 

}
