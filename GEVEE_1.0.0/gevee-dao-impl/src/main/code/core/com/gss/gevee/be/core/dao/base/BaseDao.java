package com.gss.gevee.be.core.dao.base;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.exception.GeveePersistenceException;

public abstract class BaseDao <T extends BaseEntity, ID extends Serializable>
		implements IBaseDao<T, ID>{
	
	public abstract BaseLogger getLogger();
	
	@PersistenceContext(unitName = "gevee-unit")
	private EntityManager manager;
	
	public EntityManager getManager() throws GeveePersistenceException {
		if (manager != null)
			return manager;
		else {
			GeveePersistenceException sdr = new GeveePersistenceException("Erreur de récupération de l'entity manager!");
			getLogger().error("Erreur de récupération de l'entity manager!",
					sdr);
			throw sdr;
		}
	}
	
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public <X extends BaseEntity> X save(X entity) throws GeveePersistenceException {
		try{
			getLogger().debug("Création de l'entité en BDD ...");
			this.getManager().persist(entity);
			final X saved = this.getManager().merge(entity);
			return saved;
		}catch (GeveePersistenceException e){
			throw e;
		}
	}
	
	public <X extends BaseEntity> X update(X entity) throws GeveePersistenceException {
		try{
			getLogger().debug("Mise à jour de l'entité en BDD ...");
			final X saved = this.getManager().merge(entity);
			return saved;
		}catch (GeveePersistenceException e){
			throw e;
		}
	}
	
	public <X extends BaseEntity> boolean delete(X entity) throws GeveePersistenceException {
		try{
			getLogger().debug("Suppression de l'entité en BDD ...");
			this.getManager().merge(entity);
			return true;
		}catch (GeveePersistenceException e){
			throw e;
		}
	}
	
	public <X extends BaseEntity> void remove(X entity) throws GeveePersistenceException {
		try{
			getLogger().debug("remove de l'entité en BDD ...");
			this.getManager().remove(this.getManager().merge(entity));
		}catch (GeveePersistenceException e){
			throw e;
		}
	}

}
