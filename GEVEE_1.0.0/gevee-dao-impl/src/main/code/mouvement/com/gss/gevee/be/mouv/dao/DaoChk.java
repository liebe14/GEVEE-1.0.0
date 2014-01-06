package com.gss.gevee.be.mouv.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.BaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.mouv.entity.TabChk;

@Stateless
public class DaoChk extends BaseDao<TabChk, String> implements IDaoChk{
	
	private static BaseLogger logger = BaseLogger.getLogger(DaoChk.class);
	
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
			" WHERE o.codRefChk ='" + id + "' ";
			
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
			" ORDER BY o.codRefChk ";
			
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
		TabChk currentChk = (TabChk)entity;
		if(currentChk.getTabMouv().getCodRefMouv() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabMouv.codRefMouv) like '%"+currentChk.getTabMouv().getCodRefMouv()+"%'";
		}
		if(currentChk.getTabLieu().getLibLieu() != null ){
			clauseWhere = clauseWhere + "AND upper(o.tabLieu.libLieu) like '%"+currentChk.getTabLieu().getLibLieu()+"%'";
		}
		
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o where " + clauseWhere +
			" ORDER BY o.codRefChk ";

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
