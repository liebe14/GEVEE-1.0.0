package com.gss.gevee.be.mouv.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.BaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.mouv.entity.TabOrd;

@Stateless
public class DaoOrd extends BaseDao<TabOrd, String> implements IDaoOrd{
	
	private static BaseLogger logger = BaseLogger.getLogger(DaoOrd.class);
	
	@Override
	public BaseLogger getLogger() {
		return logger;
	}

	@SuppressWarnings("unchecked")
	public <X extends BaseEntity> X findById(X entity, Serializable id)
			throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o " + 
			" WHERE o.numOrdTra='" + id + "' ";
			
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
			" ORDER BY o.numOrdTra ";
			
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
		String clauseWhere = "1=1 ";
		TabOrd currentOrd = (TabOrd)entity;
		if(currentOrd.getNumOrdTra() != null ){
			clauseWhere = clauseWhere + "AND upper(o.numOrdTra) like '%"+currentOrd.getNumOrdTra()+"%'";
		}
		if(currentOrd.getNumDoss() != null ){
			clauseWhere = clauseWhere + "AND upper(o.numDoss) like '%"+currentOrd.getNumDoss()+"%'";
		}
		if(currentOrd.getTabLieuEnlev().getLibLieu() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabLieuEnlev.libLieu) like '%"+currentOrd.getTabLieuEnlev().getLibLieu()+"%'";
		}
		if(currentOrd.getTabLieuDecha().getLibLieu() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabLieuDecha.libLieu) like '%"+currentOrd.getTabLieuDecha().getLibLieu()+"%'";
		}
		
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o where " + clauseWhere +
			" ORDER BY o.numOrdTra ";

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
