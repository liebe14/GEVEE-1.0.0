package com.gss.gevee.be.mouv.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.BaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.mouv.entity.TabCon;

@Stateless
public class DaoCon extends BaseDao<TabCon, String> implements IDaoCon{
	
	private static BaseLogger logger = BaseLogger.getLogger(DaoCon.class);
	
	@Override
	public BaseLogger getLogger() {
		return logger;
	}
	
	@SuppressWarnings("unchecked")
	public <X extends BaseEntity> X findById(X entity, Serializable id)
			throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o " + 
			" WHERE o.codCon='" + id + "' ";
			
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
			" ORDER BY o.numCon ";
			
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
		TabCon currentCon = (TabCon)entity;
		if(currentCon.getNumCon() != null && !currentCon.getNumCon().equals("")){
			clauseWhere = clauseWhere + "AND upper(o.numCon) like '%"+currentCon.getNumCon()+"%'";
		}
		if(currentCon.getTabOrdTran().getNumOrdTra()!= null ){
			clauseWhere = clauseWhere + "AND upper(o.tabOrdTran.numOrdTra) like '%"+currentCon.getTabOrdTran().getNumOrdTra()+"%'";
		}
		
		try{
			String query = "SELECT o FROM " + entity.getClass().getSimpleName() + " o where " + clauseWhere +
			" ORDER BY o.numCon ";

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
	
	@Override
	public  List<TabCon> findByNumOrd(String numOrd)
	throws GeveePersistenceException {
		try{
			String query = "SELECT o FROM TabCon o where o.tabOrdTran.numOrdTra= '" + numOrd + "' ORDER BY o.numCon ";

			logger.debug("Requete <" + query + ">");

			List<TabCon> v$list = getManager().createQuery(query).getResultList();

			getLogger().debug("Nombre d'éléments trouvés : " + (v$list == null ? "0" : v$list.size()));
			if ((v$list == null) || (v$list.size() <= 0)) {
				return new ArrayList<TabCon>();
			}
			return v$list;
		}catch(GeveePersistenceException sdr){
			throw sdr;
		}
	}



}
