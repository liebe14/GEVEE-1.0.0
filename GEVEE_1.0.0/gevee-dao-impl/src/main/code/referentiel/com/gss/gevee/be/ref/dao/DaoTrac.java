package com.gss.gevee.be.ref.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.BaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.ref.entity.TabRem;
import com.gss.gevee.be.ref.entity.TabTrac;

@Stateless
public class DaoTrac extends BaseDao<TabTrac, String> implements IDaoTrac{
	
	private static BaseLogger logger = BaseLogger.getLogger(DaoTrac.class);
	
	@Override
	public BaseLogger getLogger() {
		return logger;
	}


	@SuppressWarnings("unchecked")
	public <X extends BaseEntity> X findById(X entity, Serializable id)
			throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o " + 
			" WHERE o.numParc='" + id + "' ";
			
			logger.debug("Requete <" + query + ">");
			
			List<X> v$list = getManager().createQuery(query).getResultList();
			
			getLogger().debug("Nombre d'éléments trouvés : " + (v$list == null ? "0" : v$list.size()));
			if ((v$list == null) || (v$list.size() <= 0)) {
				return null;
			}
			return v$list.get(0);
		}catch(GeveePersistenceException sdr){
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> findAll(X entity)
			throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o " + 
			" ORDER BY o.numParc ";
			
			logger.debug("Requete <" + query + ">");
			
			List<X> v$list = getManager().createQuery(query).getResultList();
			
			getLogger().debug("Nombre d'éléments trouvés : " + (v$list == null ? "0" : v$list.size()));
			if ((v$list == null) || (v$list.size() <= 0)) {
				return new ArrayList<X>();
			}
			return v$list;
		}catch(GeveePersistenceException sdr){
			throw sdr;
		}
	}
	
	public <X extends BaseEntity> List<X> findByExample(X entity)
	throws GeveePersistenceException {
		String clauseWhere = "";
		TabTrac currentTrac = (TabTrac)entity;
		if(currentTrac.getNumParc() != null && currentTrac.getNumMat() != null){
			clauseWhere = "upper(o.numParc) like '%"+currentTrac.getNumParc()+"%' AND upper(o.numMat) like '%"+currentTrac.getNumMat()+"%'";
		}else if(currentTrac.getNumParc() == null){
			clauseWhere = "upper(o.numMat) like '%"+currentTrac.getNumMat()+"%'";
		}else if(currentTrac.getNumMat() == null){
			clauseWhere = "upper(o.numParc) like '%"+currentTrac.getNumParc()+"%'";
		}else{
			clauseWhere="1=1";
		}
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o where " + clauseWhere +
			" ORDER BY o.numParc ";

			logger.debug("Requete <" + query + ">");

			List<X> v$list = getManager().createQuery(query).getResultList();

			getLogger().debug("Nombre d'éléments trouvés : " + (v$list == null ? "0" : v$list.size()));
			if ((v$list == null) || (v$list.size() <= 0)) {
				return new ArrayList<X>();
			}
			return v$list;
		}catch(GeveePersistenceException sdr){
			throw sdr;
		}
	}


}
