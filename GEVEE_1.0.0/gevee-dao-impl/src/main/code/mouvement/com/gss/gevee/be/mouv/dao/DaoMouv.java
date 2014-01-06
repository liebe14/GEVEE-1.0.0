package com.gss.gevee.be.mouv.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.BaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.mouv.entity.TabMouv;

@Stateless
public class DaoMouv extends BaseDao<TabMouv, String> implements IDaoMouv{
	
	private static BaseLogger logger = BaseLogger.getLogger(DaoMouv.class);
	
	@Override
	public BaseLogger getLogger() {
		return logger;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X extends BaseEntity> X findById(X entity, Serializable id)
			throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o " + 
			" WHERE o.codRefMouv ='" + id + "' ";
			
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

	@SuppressWarnings("unchecked")
	@Override
	public <X extends BaseEntity> List<X> findAll(X entity)
			throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o " + 
			" ORDER BY o.codRefMouv ";
			
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

	@SuppressWarnings("unchecked")
	@Override
	public <X extends BaseEntity> List<X> findByExample(X entity)
			throws GeveePersistenceException {
		String clauseWhere = "1=1 ";
		TabMouv currentMouv = (TabMouv)entity;
		if(currentMouv.getTabDep().getCodRefDep() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabDep.codRefDep) like '%"+currentMouv.getTabDep().getCodRefDep()+"%'";
		}
		if(currentMouv.getEnuTypMouv() != null ){
			clauseWhere = clauseWhere + "AND upper(o.enuTypMouv) like '%"+currentMouv.getEnuTypMouv()+"%'";
		}
		if(currentMouv.getTabChau().getMatChau() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabChau.matChau) like '%"+currentMouv.getTabChau().getMatChau()+"%'";
		}
		if(currentMouv.getTabLieuDepar().getLibLieu() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabLieuDepar.libLieu) like '%"+currentMouv.getTabLieuDepar().getLibLieu()+"%'";
		}
		if(currentMouv.getTabLieuArriv().getLibLieu() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabLieuArriv.libLieu) like '%"+currentMouv.getTabLieuArriv().getLibLieu()+"%'";
		}
		
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o where " + clauseWhere +
			" ORDER BY o.codRefMouv ";

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
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<TabMouv> findByCodRefDep(String refDep)
	throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM TabMouv o where o.tabDep.codRefDep = '" + refDep + "' ORDER BY o.codRefMouv ";

			logger.debug("Requete <" + query + ">");

			List<TabMouv> v$list = getManager().createQuery(query).getResultList();

			getLogger().debug("Nombre d'éléments trouvés : " + (v$list == null ? "0" : v$list.size()));
			if ((v$list == null) || (v$list.size() <= 0)) {
				return new ArrayList<TabMouv>();
			}
			return v$list;
		}catch(GeveePersistenceException sdr){
			throw sdr;
		}
	}

}
