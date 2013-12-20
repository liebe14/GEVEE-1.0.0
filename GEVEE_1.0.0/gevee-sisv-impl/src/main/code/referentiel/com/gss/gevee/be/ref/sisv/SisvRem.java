package com.gss.gevee.be.ref.sisv;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.BaseSisv;
import com.gss.gevee.be.ref.dao.IDaoRem;
import com.gss.gevee.be.ref.entity.TabRem;

@Stateless
public class SisvRem extends BaseSisv<TabRem, String> implements ISisvRem{
	
	private static BaseLogger logger = BaseLogger.getLogger(TabRem.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoRem daoRem; 

	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoRem.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
			
		try {
			return daoRem.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeSystemException {
		
		try {
			return daoRem.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	@Override
	public IBaseDao<TabRem, String> getBaseDao() {
		return daoRem;
	}

}
